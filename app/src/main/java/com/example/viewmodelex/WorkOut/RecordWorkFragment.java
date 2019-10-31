package com.example.viewmodelex.WorkOut;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewmodelex.Exercieses.DatabaseHelper;
import com.example.viewmodelex.Exercieses.ExerciesesItem;
import com.example.viewmodelex.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class RecordWorkFragment extends Fragment implements RecordWorkOutAdapter.OnListItemSelectedInterface {

    private ArrayList<ExerciesesItem> mArrayList;
    private ArrayList<WorkOutItem> innerList;
    private ArrayList<String> mapTitle;
    private HashMap<String, WorkOutItem> workMap;
    private DatabaseHelper databaseHelper;
    private RecordWorkOutAdapter dialAdapter;
    private EditText searchText;
    private Button addWork;
    private Button doneWork;
    private ImageButton timerBtn;
    private RecyclerView dialRecyclerView;
    private WorkOutAdapter innerAdapter;
    private RecyclerView innerRecyclerView;
    private ExerciesesItem clickItem;
    private String clickTitle;
    private WorkOutDatabase innerDatabase;
    private Timer timer;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.workouts_fragment2, container, false);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addWork = (Button) view.findViewById(R.id.workAddWorkBtn);
        doneWork = (Button) view.findViewById(R.id.workDoneBtn);
        timerBtn = (ImageButton) view.findViewById(R.id.timberBtn);
        searchText = (EditText) view.findViewById(R.id.workDialSearch);
        mapTitle = new ArrayList<>();

        addWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callDial();
            }
        });
        doneWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSaveBtn();
            }
        });
        timerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {

                    }
                },0, 90000);
            }
        });


    }

    public void callDial() {

        final Dialog recordDial = new Dialog(getContext());
        recordDial.setContentView(R.layout.workout_dialog);

        Button cancel = recordDial.findViewById(R.id.workDialCancelBtn);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordDial.dismiss();
            }
        });

        Button saveBtn = recordDial.findViewById(R.id.workDialAddBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                innerRecyclerView = getView().findViewById(R.id.workOutList);
                LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
                innerRecyclerView.setLayoutManager(linearLayoutManager1);

                innerAdapter = new WorkOutAdapter(mapTitle, getContext());
                innerRecyclerView.setAdapter(innerAdapter);

                getData(clickItem.getExerName());
                recordDial.dismiss();
            }
        });

        dialRecyclerView = (RecyclerView) recordDial.findViewById(R.id.workDialList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        dialRecyclerView.setLayoutManager(linearLayoutManager);

        databaseHelper = new DatabaseHelper(getContext());
        mArrayList = databaseHelper.getResult();

        dialAdapter = new RecordWorkOutAdapter(mArrayList, getContext(), this);
        dialAdapter.setListener(new RecordWorkOutAdapter.OnListItemSelectedInterface() {
            @Override
            public void onItemSelected(View v, int position) {
                clickItem = mArrayList.get(position);
            }
        });
        dialRecyclerView.setAdapter(dialAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(dialRecyclerView.getContext(),
                linearLayoutManager.getOrientation());
        dialRecyclerView.addItemDecoration(dividerItemDecoration);

        addWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callDial();
            }
        });

        recordDial.show();
    }

    public void setData(Dialog newExer) {

        String search = searchText.getText().toString();

    }

    private void getData(String title) {

        mapTitle.add(title);
        innerAdapter.notifyDataSetChanged();
    }


    @Override
    public void onItemSelected(View v, int position) {
        RecordWorkOutAdapter.Holder viewHolder = (RecordWorkOutAdapter.Holder) dialRecyclerView.findViewHolderForAdapterPosition(position);
    }

    public void clickSaveBtn() {
        workMap = new HashMap<>(innerAdapter.getMapData());
        innerDatabase = new WorkOutDatabase(getContext());

        innerDatabase.insert(workMap);
        final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setMessage("저장되었습니다.");
        alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mapTitle.clear();
                innerAdapter.notifyDataSetChanged();
            }
        });
        alert.show();

    }

}
