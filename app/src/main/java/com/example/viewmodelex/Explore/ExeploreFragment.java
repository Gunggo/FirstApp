package com.example.viewmodelex.Explore;


import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.viewmodelex.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
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

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Nullable;

import io.opencensus.internal.StringUtil;

import static androidx.constraintlayout.widget.Constraints.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExeploreFragment extends Fragment {
    private static final String TAG = "lecture";

    private LineChart lineChart;
    private String[] mDate;
    LineData lineData = null;

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


        Button button = (Button) v.findViewById(R.id.weightSave);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataInsert();
            }
        });

        getData();
        return v;
    }

    public LineChart drawChart(LineChart lineChart) {

        // x축 설정
        XAxis xAxis = lineChart.getXAxis();
        mDate = getDate();
        ValueFormatter formatter = new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return mDate[(int) value];
            }
        };
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(formatter);
        xAxis.setLabelCount(6, true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.WHITE);
        xAxis.enableGridDashedLine(8, 24, 0);

        // y축 설정
        YAxis yLAxis = lineChart.getAxisLeft();
        yLAxis.setTextColor(Color.WHITE);
        yLAxis.setAxisMaximum(100);
        yLAxis.setAxisMinimum(0);
        yLAxis.setLabelCount(11);

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

    public String[] getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");

        Calendar calendar = new GregorianCalendar(Locale.KOREA);
        String[] week = new String[7];

        for (int i = 0; i < 7; i++) {
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            week[i] = sdf.format(calendar.getTime());
            System.out.println(week[i]);
        }
        return week;
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
        final ArrayList<Entry> datavalue1 = new ArrayList<>();
        final ArrayList<Entry> datavalue2 = new ArrayList<>();
        final ArrayList<Entry> datavalue3 = new ArrayList<>();

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
                        for (QueryDocumentSnapshot doc : value) {
                            if (doc.get("weight") != null) {
                                sMax = doc.getId();
                                weightValue.add(doc.getString("weight"));
                                muscleValue.add(doc.getString("muscle"));
                                fatValue.add(doc.getString("fat"));
                                for (int i = Integer.parseInt(sMax); i <= weightValue.size(); i++) {
                                    datavalue1.add(new Entry(i, Integer.parseInt(weightValue.get(i - 1))));
                                }
                                for (int i = Integer.parseInt(sMax); i <= muscleValue.size(); i++) {
                                    datavalue2.add(new Entry(i, Integer.parseInt(muscleValue.get(i - 1))));
                                }
                                for (int i = Integer.parseInt(sMax); i <= fatValue.size(); i++) {
                                    datavalue3.add(new Entry(i, Integer.parseInt(fatValue.get(i - 1))));
                                }
                            }
                        }
                        nMax = Integer.parseInt(sMax);

                        LineDataSet weightDataSet = new LineDataSet(datavalue1, "몸무게");
                        weightDataSet.setCircleColor(Color.RED);
                        weightDataSet.setColor(Color.RED);
                        weightDataSet.setCircleColorHole(Color.RED);

                        LineDataSet fatDataSet = new LineDataSet(datavalue2, "체지방");
                        fatDataSet.setCircleColor(Color.YELLOW);
                        fatDataSet.setColor(Color.YELLOW);
                        fatDataSet.setCircleColorHole(Color.YELLOW);

                        LineDataSet muscleDataSet = new LineDataSet(datavalue3, "골격근");
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

        // Add a new document with a generated ID
        String newCount = String.format("%03d", nMax + 1);

        db.collection("MyFirestoreDB")
                //.document()
                .document(newCount)
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
}
