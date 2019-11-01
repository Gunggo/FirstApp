package com.example.viewmodelex.WorkOut;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewmodelex.R;

import java.util.ArrayList;
import java.util.HashMap;

public class WorkOutAdapter extends RecyclerView.Adapter<WorkOutAdapter.Holder> {

    public static final String TAG = "lecture";
    private ArrayList<String> titleList;
    private HashMap<String, WorkOutItem> mapData;
    private ArrayList<WorkOutItem> workData;
    private ArrayList<Holder> holderList;
    private Context context;

    public WorkOutAdapter(ArrayList<String> mapList, Context context) {
        this.titleList = mapList;
        this.context = context;
        holderList = new ArrayList<>();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.workout_add, viewGroup, false);
        final Holder viewHolder = new Holder(view);
        holderList.add(viewHolder);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder viewholder, int position) {
        viewholder.etTitle.setText(titleList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.titleList.size();
    }

    public HashMap<String, WorkOutItem> getMapData() {
        mapData = new HashMap<>();
        workData = new ArrayList<>();

        for (int i = 0; i < titleList.size(); i++) {
            WorkOutItem workOutItem = new WorkOutItem();
            workOutItem.addKgList(holderList.get(i).etKg1.getText().toString());
            workOutItem.addKgList(holderList.get(i).etKg2.getText().toString());
            workOutItem.addKgList(holderList.get(i).etKg3.getText().toString());
            workOutItem.addKgList(holderList.get(i).etKg4.getText().toString());
            workOutItem.addKgList(holderList.get(i).etKg5.getText().toString());

            workOutItem.addRepList(holderList.get(i).etRep1.getText().toString());
            workOutItem.addRepList(holderList.get(i).etRep2.getText().toString());
            workOutItem.addRepList(holderList.get(i).etRep3.getText().toString());
            workOutItem.addRepList(holderList.get(i).etRep4.getText().toString());
            workOutItem.addRepList(holderList.get(i).etRep5.getText().toString());

            mapData.put(titleList.get(i),workOutItem);
        }

        return mapData;

    }


    public class Holder extends RecyclerView.ViewHolder {
        private TextView etTitle;

        private TextView etKg1;
        private TextView etKg2;
        private TextView etKg3;
        private TextView etKg4;
        private TextView etKg5;
        private TextView etRep1;
        private TextView etRep2;
        private TextView etRep3;
        private TextView etRep4;
        private TextView etRep5;

        public Holder(@NonNull View itemView) {
            super(itemView);
            this.etTitle = (TextView) itemView.findViewById(R.id.etTitle);

            this.etKg1 = (TextView) itemView.findViewById(R.id.etKg1);
            this.etKg2 = (TextView) itemView.findViewById(R.id.etKg2);
            this.etKg3 = (TextView) itemView.findViewById(R.id.etKg3);
            this.etKg4 = (TextView) itemView.findViewById(R.id.etKg4);
            this.etKg5 = (TextView) itemView.findViewById(R.id.etKg5);

            this.etRep1 = (TextView) itemView.findViewById(R.id.etRep1);
            this.etRep2 = (TextView) itemView.findViewById(R.id.etRep2);
            this.etRep3 = (TextView) itemView.findViewById(R.id.etRep3);
            this.etRep4 = (TextView) itemView.findViewById(R.id.etRep4);
            this.etRep5 = (TextView) itemView.findViewById(R.id.etRep5);

        }
    }
}
