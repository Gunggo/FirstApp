package com.example.viewmodelex.Exercieses;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewmodelex.R;

import java.util.List;

public class ExerciesesAdapter extends RecyclerView.Adapter<ExerciesesAdapter.Holder> {
    public static final String TAG = "lecture";

    private List<ExerciesesItem> mList;
        Context context;

    public ExerciesesAdapter(List<ExerciesesItem> list, Context context) {
        this.mList = list;
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.exer_item, viewGroup, false);
        final Holder viewHolder = new Holder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder viewholder, int position) {

        viewholder.exerName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        viewholder.category.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);

        viewholder.exerName.setText(mList.get(position).getExerName());
        viewholder.category.setText(mList.get(position).getCategory());

        final String item = mList.get(position).toString();
        viewholder.exerName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog exerLog = new Dialog(context);
                exerLog.setContentView(R.layout.dialog_exer_log);

                Button cancel = exerLog.findViewById(R.id.button4);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        exerLog.dismiss();
                    }
                });

                Button saveBtn = exerLog.findViewById(R.id.exerSave);
//                saveBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        setData(exerLog);
//                        exerLog.dismiss();
//                    }
//                });
                exerLog.show();
            }
        });

        viewholder.category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.mList.size();
    }


    public class Holder extends RecyclerView.ViewHolder{
        private TextView exerName;
        private TextView category;

        public Holder(@NonNull View itemView) {
            super(itemView);
            this.exerName = (TextView) itemView.findViewById(R.id.exerName);
            this.category = (TextView) itemView.findViewById(R.id.exerCate);
        }

    }

    public void viewDialog() {

    }
}
