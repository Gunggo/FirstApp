package com.example.viewmodelex.WorkOut;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewmodelex.Exercieses.ExerciesesItem;
import com.example.viewmodelex.R;

import java.util.List;

public class RecordWorkOutAdapter extends RecyclerView.Adapter<RecordWorkOutAdapter.Holder> {

//    public interface OnListItemLongSelectedInterface {
//        void onItemLongSelected(View v, int position);
//    }

    public interface OnListItemSelectedInterface {
        void onItemSelected(View v, int position);
    }

    private OnListItemSelectedInterface mListener;
//    private OnListItemLongSelectedInterface mLongListener;
    public static final String TAG = "lecture";
    private List<ExerciesesItem> mList;
    private Context context;
    private SparseBooleanArray mSelectedItems = new SparseBooleanArray(0);

    public RecordWorkOutAdapter(List<ExerciesesItem> list, Context context, OnListItemSelectedInterface listener) {
        this.mList = list;
        this.context = context;
        this.mListener = listener;
    }

    public void setListener(OnListItemSelectedInterface listener) {
        this.mListener = listener;
    }

//    public void setData(List<ExerciesesItem> data) {
//        mList = data;
//        notifyDataSetChanged();
//    }

    @Override
    public Holder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.exer_item, viewGroup, false);
        final Holder viewHolder = new Holder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder viewholder, int position) {

//        viewholder.exerName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
//        viewholder.category.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);

        viewholder.exerName.setText(mList.get(position).getExerName());
        viewholder.category.setText(mList.get(position).getCategory());

        viewholder.itemView.setSelected(isItemSelected(position));
    }

    @Override
    public int getItemCount() {
        return this.mList.size();
    }

    private void toggleItemSelected(int position) {
        if (mSelectedItems.get(position, false)) {
            mSelectedItems.delete(position);
            notifyItemChanged(position);
        } else {
            mSelectedItems.put(position, true);
//            ExerciesesItem clickItem = mList.get(position);
            notifyItemChanged(position);
        }
    }

    private boolean isItemSelected(int position) {
        return mSelectedItems.get(position, false);
    }

    public void clearSelectedItem() {
        int position;

        for (int i = 0; i < mSelectedItems.size(); i++) {
            position = mSelectedItems.keyAt(i);
            mSelectedItems.put(position, false);
            notifyItemChanged(position);
        }

        mSelectedItems.clear();
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
                    toggleItemSelected(position);
                    if (position != RecyclerView.NO_POSITION) {
                        mListener.onItemSelected(v, position);
                    }
                }
            });
        }
    }
}
