package com.example.viewmodelex.Log;


import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.example.viewmodelex.R;
import com.example.viewmodelex.WorkOut.WorkOutAdapter;
import com.example.viewmodelex.WorkOut.WorkOutDatabase;
import com.example.viewmodelex.WorkOut.WorkOutItem;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class LogsFragment extends Fragment {

    MaterialCalendarView materialCalendarView;
    private RecyclerView recyclerView;
    private LogAdapter adapter;
    private WorkOutDatabase workOutDatabase;
    private HashMap<String, WorkOutItem> logData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.logs_fragment, container, false);

        materialCalendarView = new MaterialCalendarView(getContext());
        materialCalendarView = v.findViewById(R.id.calendarLog);

        materialCalendarView.setCurrentDate(new Date(System.currentTimeMillis()));
        materialCalendarView.setDateSelected(new Date(System.currentTimeMillis()),false);
        materialCalendarView.setSelectedDate(new Date(System.currentTimeMillis()));

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                recyclerView = getView().findViewById(R.id.workOutList);
                LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(linearLayoutManager1);

                workOutDatabase = new WorkOutDatabase(getContext());
                adapter = new LogAdapter(logData, getContext());
                recyclerView.setAdapter(adapter);
            }
        });

        return inflater.inflate(R.layout.logs_fragment, container, false);
    }
}
