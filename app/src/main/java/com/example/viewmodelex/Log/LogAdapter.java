package com.example.viewmodelex.Log;

import android.app.Dialog;
import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewmodelex.R;
import com.example.viewmodelex.WorkOut.WorkOutItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LogAdapter extends RecyclerView.Adapter<LogAdapter.Holder>{
    public static final String TAG = "lecture";

    private HashMap<String, ArrayList<WorkOutItem>> mapData;
    Context context;

    public LogAdapter(HashMap<String, ArrayList<WorkOutItem>> mapData, Context context) {
        this.mapData = mapData;
        this.context = context;
    }

    @Override
    public LogAdapter.Holder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.log_item, viewGroup, false);
        final Holder viewHolder = new Holder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LogAdapter.Holder viewholder, int position) {

        viewholder.workOutDate.setText(mList.get(position).getExerName());

        viewholder.etLogKg1.setText(mList.get(position).getCategory());
        viewholder.etLogKg2.setText(mList.get(position).getCategory());
        viewholder.etLogKg3.setText(mList.get(position).getCategory());
        viewholder.etLogKg4.setText(mList.get(position).getCategory());
        viewholder.etLogKg5.setText(mList.get(position).getCategory());

        viewholder.etLogRep1.setText(mList.get(position).getCategory());
        viewholder.etLogRep2.setText(mList.get(position).getCategory());
        viewholder.etLogRep3.setText(mList.get(position).getCategory());
        viewholder.etLogRep4.setText(mList.get(position).getCategory());
        viewholder.etLogRep5.setText(mList.get(position).getCategory());

    }

    @Override
    public int getItemCount() {
        return this.mapData.size();
    }


    public class Holder extends RecyclerView.ViewHolder {
        private TextView workOutDate;

        private TextView etLogKg1;
        private TextView etLogKg2;
        private TextView etLogKg3;
        private TextView etLogKg4;
        private TextView etLogKg5;
        private TextView etLogRep1;
        private TextView etLogRep2;
        private TextView etLogRep3;
        private TextView etLogRep4;
        private TextView etLogRep5;


        public Holder(@NonNull View itemView) {
            super(itemView);
            this.workOutDate = (TextView) itemView.findViewById(R.id.workOutDate);

            this.etLogKg1 = (TextView) itemView.findViewById(R.id.etLogKg1);
            this.etLogKg2 = (TextView) itemView.findViewById(R.id.etLogKg2);
            this.etLogKg3 = (TextView) itemView.findViewById(R.id.etLogKg3);
            this.etLogKg4 = (TextView) itemView.findViewById(R.id.etLogKg4);
            this.etLogKg5 = (TextView) itemView.findViewById(R.id.etLogKg5);
            this.etLogRep1 = (TextView) itemView.findViewById(R.id.etLogRep1);
            this.etLogRep2 = (TextView) itemView.findViewById(R.id.etLogRep2);
            this.etLogRep3 = (TextView) itemView.findViewById(R.id.etLogRep3);
            this.etLogRep4 = (TextView) itemView.findViewById(R.id.etLogRep4);
            this.etLogRep5 = (TextView) itemView.findViewById(R.id.etLogRep5);

        }
    }
}
