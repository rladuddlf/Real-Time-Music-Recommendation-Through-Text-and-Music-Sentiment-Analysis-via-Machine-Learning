package com.example.gogoooma.graduationproject;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAxisValueFormatter implements IAxisValueFormatter {
    private String[] mValues;

    SimpleDateFormat sdf = new SimpleDateFormat("MM월dd일");

    public DateAxisValueFormatter(String[] values) {
        this.mValues = values; }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return sdf.format(new Date((long) value));
    }
}
