package com.example.viewmodelex.Explore;


import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExeploreFragment extends Fragment {

    private LineChart lineChart;
    private LineChart lineChart2;
    private LineChart lineChart3;
    private String[] mDate;

    public ExeploreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_exeplore, container, false);

        lineChart = (LineChart) v.findViewById(R.id.weight_chart);
        drawChart(lineChart);

        return v;
    }

    public LineChart drawChart(LineChart lineChart) {
//        int[] weight = {0, 20, 40, 60, 80, 100, 120};
//        lineChart.clear();


        LineDataSet weightDataSet = new LineDataSet(weightGraph(), "몸무게");
        LineDataSet fatDataSet = new LineDataSet(fatGraph(), "체지방");
        LineDataSet muscleDataSet = new LineDataSet(muscleGraph(), "제지방");

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        resetChart(weightDataSet);
        resetChart(fatDataSet);
        resetChart(muscleDataSet);

        dataSets.add(weightDataSet);
        dataSets.add(fatDataSet);
        dataSets.add(muscleDataSet);

        LineData lineData = new LineData(dataSets);
        lineChart.setData(lineData);
        lineChart.invalidate();

        // x축 설정
        XAxis xAxis = lineChart.getXAxis();
        mDate = getDate();
        IAxisValueFormatter formatter = new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
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
        lineChart.animateY(1000, Easing.EasingOption.EaseInCubic);

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

    public ArrayList<Entry> weightGraph() {
        final ArrayList<Entry> datavalue = new ArrayList<>();
        datavalue.add(new Entry(0, 1f));
        datavalue.add(new Entry(1, 2f));
        datavalue.add(new Entry(2, 3f));
        datavalue.add(new Entry(3, 4f));
        datavalue.add(new Entry(4, 5f));
        datavalue.add(new Entry(5, 6f));
        datavalue.add(new Entry(6, 7f));

        return datavalue;
    }

    public ArrayList<Entry> fatGraph() {
        final ArrayList<Entry> datavalue = new ArrayList<>();
        datavalue.add(new Entry(6, 7f));
        datavalue.add(new Entry(5, 6f));
        datavalue.add(new Entry(4, 5f));
        datavalue.add(new Entry(3, 4f));
        datavalue.add(new Entry(2, 3f));
        datavalue.add(new Entry(1, 2f));
        datavalue.add(new Entry(0, 1f));

        return datavalue;
    }

    public ArrayList<Entry> muscleGraph() {
        final ArrayList<Entry> datavalue = new ArrayList<>();
        datavalue.add(new Entry(1, 1f));
        datavalue.add(new Entry(3, 3f));
        datavalue.add(new Entry(5, 5f));
        datavalue.add(new Entry(7, 7f));
        datavalue.add(new Entry(9, 5f));
        datavalue.add(new Entry(11, 3f));
        datavalue.add(new Entry(13, 1f));

        return datavalue;
    }

    public LineDataSet resetChart(LineDataSet lineDataSet) {
        lineDataSet.setLineWidth(2);
        lineDataSet.setCircleRadius(6);
        lineDataSet.setCircleColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet.setCircleColorHole(Color.BLUE);
        lineDataSet.setColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet.setDrawCircleHole(true);
        lineDataSet.setDrawCircles(true);
        lineDataSet.setDrawHorizontalHighlightIndicator(false);
        lineDataSet.setDrawHighlightIndicators(false);
        lineDataSet.setDrawValues(false);

        return lineDataSet;
    }

}
