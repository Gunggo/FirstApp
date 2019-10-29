package com.example.viewmodelex.WorkOut;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewmodelex.Exercieses.ExerciesesItem;
import com.example.viewmodelex.R;

import java.util.List;

public class WorkOutAdapter extends RecyclerView.Adapter<WorkOutAdapter.Holder> {

    public interface OnListItemLongSelectedInterface {
        void onItemLongSelected(View v, int position);
    }

    public interface OnListItemSelectedInterface {
        void onItemSelected(View v, int position);
    }

    private OnListItemSelectedInterface mListener;
    private OnListItemLongSelectedInterface mLongListener;
    public static final String TAG = "lecture";
    private List<ExerciesesItem> mList;
    private Context context;

    public WorkOutAdapter(List<ExerciesesItem> list, Context context, OnListItemSelectedInterface listener) {
        this.mList = list;
        this.context = context;
        this.mListener = listener;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.exer_item, viewGroup, false);
        final Holder viewHolder = new Holder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder viewholder, int position) {

        viewholder.exerName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        viewholder.category.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);

        viewholder.exerName.setText(mList.get(position).getExerName());
        viewholder.category.setText(mList.get(position).getCategory());

//        viewholder.exerName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                viewholder.itemView.setBackgroundColor(Color.BLUE);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return this.mList.size();
    }


    public class Holder extends RecyclerView.ViewHolder {
        private TextView exerName;
        private TextView category;

        public Holder(@NonNull View itemView) {
            super(itemView);
            this.exerName = (TextView) itemView.findViewById(R.id.exerName);
            this.category = (TextView) itemView.findViewById(R.id.exerCate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    mListener.onItemSelected(v, getAdapterPosition());

                }
            });
        }

    }

    public void viewDialog() {

    }
}
