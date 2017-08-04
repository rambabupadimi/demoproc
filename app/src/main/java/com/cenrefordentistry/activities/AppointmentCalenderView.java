package com.cenrefordentistry.activities;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.cenrefordentistry.R;
import com.squareup.timessquare.CalendarPickerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class AppointmentCalenderView extends AppCompatActivity {


    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("dd-MM-yyyy");
    private Collection<Date> selectedDates = new ArrayList<>();
    private Collection<Date> highlightedDates = new ArrayList<>();


    CalendarPickerView calendar;
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_calender_view);

        calendar = (CalendarPickerView) findViewById(R.id.calendar_view);
        Date today = new Date();


        try {
            Date date1 = dateFormat.parse("02-8-2017");
            Date date2 = dateFormat.parse("03-8-2017");
            Date date3 = dateFormat.parse("06-8-2017");
            Date date4 = dateFormat.parse("07-8-2017");
            Date date5 = dateFormat.parse("09-8-2017");
            Date date6 = dateFormat.parse("13-8-2017");
            Date date7 = dateFormat.parse("15-8-2017");
            selectedDates.add(date1);
            selectedDates.add(date2);
            selectedDates.add(date3);
            selectedDates.add(date4);
            selectedDates.add(date5);
            selectedDates.add(date6);
            selectedDates.add(date7);
            Date date11 = dateFormat.parse("22-8-2017");
            Date date12 = dateFormat.parse("23-8-2017");
            Date date13 = dateFormat.parse("26-8-2017");
            highlightedDates.add(date11);
            highlightedDates.add(date12);
            highlightedDates.add(date13);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            final Calendar nextYear = Calendar.getInstance();
            nextYear.add(Calendar.MONTH, 2);
            calendar.init(today, nextYear.getTime()).inMode(CalendarPickerView.SelectionMode.MULTIPLE).withSelectedDates(selectedDates);
            calendar.highlightDates(highlightedDates);

        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
