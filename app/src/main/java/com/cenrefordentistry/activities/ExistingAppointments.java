package com.cenrefordentistry.activities;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.cenrefordentistry.R;
import com.cenrefordentistry.adapters.ExistingAppointmentsAdapter;
import com.cenrefordentistry.adapters.MessagesAdapter;

public class ExistingAppointments extends AppCompatActivity {

    private RecyclerView exstapRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_existing_appointments);


        Toolbar toolbar = (Toolbar) findViewById(R.id.existing_appointment_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#007C32"));
        }

        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.colorPrimaryDarker), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);


        exstapRecyclerView   = (RecyclerView)findViewById(R.id.exstap_recyclerview);
        exstapRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ExistingAppointmentsAdapter existingAppointmentsAdapter = new ExistingAppointmentsAdapter(this);
        exstapRecyclerView.setAdapter(existingAppointmentsAdapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== android.R.id.home)
        {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
