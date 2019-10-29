package com.example.viewmodelex.WorkOut;


import android.icu.text.SimpleDateFormat;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.viewmodelex.MainActivity;
import com.example.viewmodelex.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class WorkoutsFragment extends Fragment {
    public static final String TAG = "lecture";

    FirebaseFirestore db;

    public WorkoutsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.workouts_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Button button1 = (Button) view.findViewById(R.id.workOutStart);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).callRecord();
            }
        });
    }

    //    private void dataInsert() {
//        Map<String, Object> quote = new HashMap<>();
//        quote.put("Set", etSet.getText().toString());
//        quote.put("Weight", etWeight.getText().toString());
//        quote.put("Rep", etRep.getText().toString());
////        quote.put("num", String.format("%03d", nMax + 1));
////        quote.put("date", getToday());
////        String newCount = String.format("%03d", nMax + 1);
//
//        db.collection("WorkInfo") // 나중에 컬렉션 이름을 회원 아이디로
//                //.document()
//                .document(getDay()) // 문서 이름은 각 날짜로
//                .set(quote)
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        Log.d(TAG, "DocumentSnapshot successfully written!");
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w(TAG, "Error writing document", e);
//                    }
//                });
//        getData();
//    }

//    public String getDay() {
//        SimpleDateFormat sdf = new SimpleDateFormat("MM.dd HH:mm", Locale.KOREA);
//        Calendar calendar = new GregorianCalendar(Locale.KOREA);
//        calendar.setTime(new Date());
//        return sdf.format(calendar.getTime());
//    }



}
