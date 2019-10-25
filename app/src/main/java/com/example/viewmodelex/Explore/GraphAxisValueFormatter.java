package com.example.viewmodelex.Explore;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

public class GraphAxisValueFormatter implements IAxisValueFormatter {
    private String[] mValues;

    GraphAxisValueFormatter(String[] values) {
        this.mValues = values;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        if (value >= 0) {
            if (mValues.length > (int) value) {
                return mValues[(int) value];
            } else return "";
        } else {
            return "";
        }
//        return mValues[(int)value];
    }
}
