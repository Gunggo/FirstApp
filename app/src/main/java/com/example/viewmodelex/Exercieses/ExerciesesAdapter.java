package com.example.viewmodelex.Exercieses;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewmodelex.R;

import java.util.List;

public class ExerciesesAdapter extends RecyclerView.Adapter<ExerciesesAdapter.Holder> {

    private List<ExerciesesItem> mList;
    private ExerciesesItem data;
//    Context context = null;

    public ExerciesesAdapter(List<ExerciesesItem> list) {
        this.mList = list;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.exer_item, viewGroup, false);

        Holder viewHolder = new Holder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder viewholder, int position) {

        viewholder.exerName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        viewholder.category.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);

        viewholder.exerName.setText(mList.get(position).getExerName());
        viewholder.category.setText(mList.get(position).getCategory());


    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }


    public class Holder extends RecyclerView.ViewHolder {
        protected TextView exerName;
        protected TextView category;


        public Holder(@NonNull View itemView) {
            super(itemView);
            this.exerName = (TextView) itemView.findViewById(R.id.exerName);
            this.category = (TextView) itemView.findViewById(R.id.exerCate);

        }

    }


}
