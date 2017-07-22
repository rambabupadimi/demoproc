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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AppointmentCalenderView extends AppCompatActivity {

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_calender_view);

        final List<EventDay> events = new ArrayList<>();

        final Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 2);
        events.add(new EventDay(calendar, R.drawable.appointment_rectangle_drawable));

        final CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);
        calendarView.setEvents(events);

        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                Toast.makeText(getApplicationContext(),
                        eventDay.getCalendar().getTime().toString(),

                        Toast.LENGTH_SHORT).show();

               // calendarView.setDate(R.drawable.appointment_rectangle_drawable);
           //     calendarView.setBackgroundResource(R.drawable.appointment_rectangle_drawable);


            }
        });

    }
}
