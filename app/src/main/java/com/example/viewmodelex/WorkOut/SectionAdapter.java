//package com.example.viewmodelex.WorkOut;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.viewmodelex.R;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//public class SectionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
//    public static final String TAG = "check";
//
//    private final int TYPE_HEADER = 0;
//    private final int TYPE_ITEM = 1;
//    private final int TYPE_FOOTER = 2;
//
//    private Context context;
//    private ArrayList<WorkOutItem> listData;
//    private HashMap<String, ArrayList<WorkOutItem>> listDatas;
//    private OnListItemSelectedListener selListener;
//
//    public SectionAdapter(Context context, HashMap<String, ArrayList<WorkOutItem>> listDatas) {
//        this.context = context;
//        this.listDatas = listDatas;
//    }
//
//    public SectionAdapter() {
//    }
//
//    public interface OnListItemSelectedListener {
//        void onItemSelected(View v, int position);
//    }
//
//    public void setListener(OnListItemSelectedListener listener) {
//        this.selListener = listener;
//    }
//
//    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        context = parent.getContext();
//        RecyclerView.ViewHolder holder;
//        View view;
//        if (viewType == TYPE_HEADER) {
//            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.workout_header, parent, false);
//            holder = new HeaderViewHolder(view);
//        } else if (viewType == TYPE_FOOTER) {
//            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.workout_footer, parent, false);
//            holder = new FooterViewHolder(view);
//        } else {
//            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.workout_item, parent, false);
//            holder = new ItemViewHolder(view);
//
//        }
//
//        return holder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//
//        if (holder instanceof HeaderViewHolder) {
//            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
//            headerViewHolder.onBind(listData.get(position));
//        } else if (holder instanceof FooterViewHolder) {
//            FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
//        } else {
//            // Item을 하나, 하나 보여주는(bind 되는) 함수입니다.
//            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
//            itemViewHolder.onBind(listData.get(position - 1));
//        }
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        if (position == 0)
//            return TYPE_HEADER;
//        else if (position == listData.size() + 1)
//            return TYPE_FOOTER;
//        else
//            return TYPE_ITEM;
//    }
//
//    @Override
//    public int getItemCount() {
//        return listData.size() + 2;
//    }
//
//    void addItem(WorkOutItem data) {
//        listData.add(data);
//    }
//
//    public List<WorkOutItem> getListData() {
//        return listData;
//    }
//
//    class HeaderViewHolder extends RecyclerView.ViewHolder {
//        private TextView textView;
//
//        HeaderViewHolder(View headerView) {
//
//            super(headerView);
//            textView = itemView.findViewById(R.id.etTitle);
//        }
//
//        void onBind(WorkOutItem data) {
//            textView.setText(data.getTitle());
//        }
//    }
//
//    class FooterViewHolder extends RecyclerView.ViewHolder {
//        private Button addSet;
//
//        FooterViewHolder(View footerView) {
//            super(footerView);
//            addSet = footerView.findViewById(R.id.workAddSetBtn2);
//            addSet.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    addItem(new WorkOutItem("0", "0", Integer.toString(listData.size() + 1)));
//                    notifyDataSetChanged();
////                    int position = getAdapterPosition();
////                    if (position != RecyclerView.NO_POSITION) {
////                        selListener.onItemSelected(v, position);
////                    }
//                }
//            });
//        }
//    }
//
//    class ItemViewHolder extends RecyclerView.ViewHolder {
//
//        private TextView textView1;
//        private TextView textView2;
//        private TextView textView3;
//
//        ItemViewHolder(View itemView) {
//            super(itemView);
//
//            textView1 = itemView.findViewById(R.id.etSet);
//            textView2 = itemView.findViewById(R.id.etKg);
//            textView3 = itemView.findViewById(R.id.etRep);
//        }
//
//        void onBind(WorkOutItem data) {
//            textView1.setText(data.getKilogram());
//            textView2.setText(data.getSet());
//            textView3.setText(data.getRep());
//        }
//    }
//}
