package com.example.viewmodelex.WorkOut;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewmodelex.Exercieses.DatabaseHelper;
import com.example.viewmodelex.Exercieses.ExerciesesItem;
import com.example.viewmodelex.Exercieses.WorkOutDatabase;
import com.example.viewmodelex.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecordWorkFragment extends Fragment implements RecordWorkOutAdapter.OnListItemSelectedInterface {

    private List<ExerciesesItem> mArrayList;
    private List<WorkOutItem> innerList;
    private DatabaseHelper databaseHelper;
    private WorkOutDatabase innerDatabase;
    private EditText searchText;
    private RecordWorkOutAdapter dialAdapter;
    private WorkOutAdapter innerAdapter;
    private Button addWork;
    private RecyclerView dialRecyclerView;
    private RecyclerView innerRecyclerView;
    private ExerciesesItem clickItem;


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
        searchText = (EditText) view.findViewById(R.id.workDialSearch);

        addWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callDial();
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
                if (innerRecyclerView == null) {
                    innerRecyclerView = getView().findViewById(R.id.workOutList);
                    LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
                    innerRecyclerView.setLayoutManager(linearLayoutManager1);

                    innerList = new WorkOutAdapter().getListData();
                    if (innerList == null) {
                        innerList = new ArrayList<>();
                    }

                }
                innerAdapter = new WorkOutAdapter(getContext(), innerList);
                innerRecyclerView.setAdapter(innerAdapter);

                getData(clickItem);
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

    private void getData(ExerciesesItem clickItem) {

        WorkOutItem data = new WorkOutItem();
        data.setKilogram("0");
        data.setSet("1");
        data.setRep("0");
        data.setTitle(clickItem.getExerName());

        innerList.add(data);
        innerAdapter.notifyDataSetChanged();
    }


    @Override
    public void onItemSelected(View v, int position) {
        RecordWorkOutAdapter.Holder viewHolder = (RecordWorkOutAdapter.Holder) dialRecyclerView.findViewHolderForAdapterPosition(position);
    }

}
