package com.example.viewmodelex.Explore;


import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.viewmodelex.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Nullable;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExeploreFragment extends Fragment {
    private static final String TAG = "lecture";

    private LineChart lineChart;
    private String[] mDate;
    private int dateCheck = 0;
    LineData lineData = null;
    private ArrayList<String> dayValue = new ArrayList<>();


    FirebaseFirestore db;

    EditText etWeight;
    EditText etMuscle;
    EditText etFat;
    int nMax = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_exeplore, container, false);

        lineChart = (LineChart) v.findViewById(R.id.weight_chart);
        etWeight = v.findViewById(R.id.etWeight);
        etMuscle = v.findViewById(R.id.etMuscle);
        etFat = v.findViewById(R.id.etFat);
        db = FirebaseFirestore.getInstance();


        Button button1 = (Button) v.findViewById(R.id.weightSave);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataInsert();
            }
        });

        Button button2 = (Button) v.findViewById(R.id.btn_week);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateCheck = 0;
                getData();
            }
        });

        Button button3 = (Button) v.findViewById(R.id.btn_month);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateCheck = 1;
                getData();
            }
        });

        Button button4 = (Button) v.findViewById(R.id.btn_alldays);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateCheck = 2;
                getData();
            }
        });

        getData();
        return v;
    }

    public LineChart drawChart(LineChart lineChart) {

        Legend legend = lineChart.getLegend();
        legend.setTextColor(Color.WHITE);

        // x축 설정
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(new GraphAxisValueFormatter(mDate)); // x축 label
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(7, false); // 받아온 값을 몇개로 나눠서 보여줌 ?
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.WHITE);
        xAxis.enableGridDashedLine(8, 24, 0); // 힌트 그대로

        // y축 설정
        YAxis yLAxis = lineChart.getAxisLeft();
        yLAxis.setTextColor(Color.WHITE);
        yLAxis.setAxisMaximum(130);
        yLAxis.setAxisMinimum(0);
        yLAxis.setLabelCount(10, true);

        // y축 비활성화
        YAxis yRAxis = lineChart.getAxisRight();
        yRAxis.setDrawLabels(false);
        yRAxis.setDrawAxisLine(false);
        yRAxis.setDrawGridLines(false);

        Description description = new Description();
        description.setText("");

        MyMarkerView marker = new MyMarkerView(getActivity(), R.layout.markger_view);
        marker.setChartView(lineChart);
        lineChart.setMarker(marker);

        lineChart.setDoubleTapToZoomEnabled(false);
        lineChart.setDrawGridBackground(false);
        lineChart.setDescription(description);
        lineChart.animateY(500, Easing.EasingOption.EaseInCubic);

        return lineChart;
    }

    public LineDataSet chartSetting(LineDataSet lineDataSet) {
        lineDataSet.setLineWidth(2);
        lineDataSet.setCircleRadius(6);
        lineDataSet.setDrawCircleHole(true);
        lineDataSet.setDrawCircles(true);
        lineDataSet.setDrawHorizontalHighlightIndicator(false);
        lineDataSet.setDrawHighlightIndicators(false);
        lineDataSet.setDrawValues(false);

        return lineDataSet;
    }

    public void getData() {
        // 서버에서 일단 가져온 후에도 서버에서 데이터가 변경되면
        // 폰의 화면에서도 실시간으로 자동 갱신 된다.
        int LIMIT = 30;
        final ArrayList<Entry> weightEntry = new ArrayList<>();
        final ArrayList<Entry> muscleEntry = new ArrayList<>();
        final ArrayList<Entry> fatEntry = new ArrayList<>();

        final ArrayList<String> weightValue = new ArrayList<>();
        final ArrayList<String> muscleValue = new ArrayList<>();
        final ArrayList<String> fatValue = new ArrayList<>();

        db.collection("MyFirestoreDB")
//                .orderBy("name", Query.Direction.DESCENDING)
//                .limit(LIMIT)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.d(TAG, "Listen failed.", e);
                            return;
                        }

                        String sMax = "0";
                        dayValue.clear();
                        for (QueryDocumentSnapshot doc : value) {
                            if (doc.get("weight") != null) {
                                if (sMax == null) {
                                    sMax = "0";
                                } else {
                                    sMax = doc.getString("num");
                                }
                                weightValue.add(doc.getString("weight"));
                                muscleValue.add(doc.getString("muscle"));
                                fatValue.add(doc.getString("fat"));
                                dayValue.add(doc.getId().substring(0, doc.getId().lastIndexOf(" ")));
                                int count = weightValue.size() - 1;
//                                for (int i = weightValue.size() - 1; i < weightValue.size(); i++) {
                                weightEntry.add(new Entry(count, Integer.parseInt(weightValue.get(count))));
//                                }
//                                for (int i = Integer.parseInt(sMax); i < muscleValue.size(); i++) {
                                muscleEntry.add(new Entry(count, Integer.parseInt(muscleValue.get(count))));
//                                }
//                                for (int i = Integer.parseInt(sMax); i < fatValue.size(); i++) {
                                fatEntry.add(new Entry(count, Integer.parseInt(fatValue.get(count))));
//                                }
                            }
                        }
                        nMax = Integer.parseInt(sMax);
                        if (dateCheck == 0) {
                            mDate = getWeek();
                        } else if (dateCheck == 1) {
                            mDate = getMonth();
                        } else if (dateCheck == 2) {
                            mDate = getAll();
                        }

                        LineDataSet weightDataSet = new LineDataSet(weightEntry, "몸무게");
                        weightDataSet.setCircleColor(Color.RED);
                        weightDataSet.setColor(Color.RED);
                        weightDataSet.setCircleColorHole(Color.RED);

                        LineDataSet fatDataSet = new LineDataSet(muscleEntry, "체지방");
                        fatDataSet.setCircleColor(Color.YELLOW);
                        fatDataSet.setColor(Color.YELLOW);
                        fatDataSet.setCircleColorHole(Color.YELLOW);

                        LineDataSet muscleDataSet = new LineDataSet(fatEntry, "골격근");
                        muscleDataSet.setCircleColor(Color.BLUE);
                        muscleDataSet.setColor(Color.BLUE);
                        muscleDataSet.setCircleColorHole(Color.BLUE);

                        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

                        chartSetting(weightDataSet);
                        chartSetting(fatDataSet);
                        chartSetting(muscleDataSet);

                        dataSets.add(weightDataSet);
                        dataSets.add(fatDataSet);
                        dataSets.add(muscleDataSet);

                        lineData = new LineData(dataSets);
                        lineChart.setData(lineData);
                        if (lineChart.isEmpty()) {
                            lineChart.clear();
                        } else {
                            lineChart.invalidate();
                            drawChart(lineChart);
                        }
                    }
                });
    }

    private void dataInsert() {
        Map<String, Object> quote = new HashMap<>();
        quote.put("weight", etWeight.getText().toString());
        quote.put("muscle", etMuscle.getText().toString());
        quote.put("fat", etFat.getText().toString());
        quote.put("num", String.format("%03d", nMax + 1));
//        quote.put("date", getToday());
//        String newCount = String.format("%03d", nMax + 1);

        db.collection("MyFirestoreDB") // 나중에 컬렉션 이름을 회원 아이디로
                //.document()
                .document(getDay()) // 문서 이름은 각 날짜로
                .set(quote)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
        getData();
    }


    public String getDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM.dd HH:mm", Locale.KOREA);
        Calendar calendar = new GregorianCalendar(Locale.KOREA);
        return sdf.format(calendar.getTime());
    }

    public String[] getWeek() {
        String[] week = new String[7];

        for (int i = 0; i < dayValue.size(); i++) {
            week[i] = dayValue.get(i);
            if (i == 7) {
                break;
            }
        }
        return week;
    }

    public String[] getMonth() {
        String[] week = new String[30];

        for (int i = 0; i < dayValue.size(); i++) {
            week[i] = dayValue.get(i);
            if (i == 30) {
                break;
            }
        }
        return week;
    }

    public String[] getAll() {
        String[] week = new String[300];

        for (int i = 0; i < dayValue.size(); i++) {
            week[i] = dayValue.get(i);
            if (i == 30) {
                break;
            }
        }
        return week;
    }


}
