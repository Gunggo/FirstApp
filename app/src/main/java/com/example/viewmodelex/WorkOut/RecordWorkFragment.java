package com.example.viewmodelex.WorkOut;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewmodelex.Exercieses.DatabaseHelper;
import com.example.viewmodelex.Exercieses.ExerciesesAdapter;
import com.example.viewmodelex.Exercieses.ExerciesesItem;
import com.example.viewmodelex.R;

import java.util.List;

public class RecordWorkFragment extends Fragment implements WorkOutAdapter.OnListItemSelectedInterface {

    private List<ExerciesesItem> mArrayList;
    private DatabaseHelper databaseHelper;
    private EditText searchText;
    private WorkOutAdapter eAdapter;
    private Button addWork;
    private Button addSet;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.workouts_fragment2, container, false);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addWork = (Button)view.findViewById(R.id.workAddWorkBtn);
        addSet = (Button)view.findViewById(R.id.workAddSetBtn);
        searchText = (EditText)view.findViewById(R.id.workDialSearch);

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
                addSet.setVisibility(View.VISIBLE);
                recordDial.dismiss();
            }
        });

        RecyclerView mRecyclerView = (RecyclerView) recordDial.findViewById(R.id.workDialList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);

        databaseHelper = new DatabaseHelper(getContext());
        mArrayList = databaseHelper.getResult();

        eAdapter = new WorkOutAdapter(mArrayList, getContext(), this);
        mRecyclerView.setAdapter(eAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                linearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);

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

    @Override
    public void onItemSelected(View v, int position) {
        StdRecyclerAdapter.StdViewHolder viewHolder = (StdRecyclerAdapter.StdViewHolder)recyclerView.findViewHolderForAdapterPosition(position);
        Toast.makeText(this, viewHolder.textView.getText().toString(), Toast.LENGTH_SHORT).show();
    }
}