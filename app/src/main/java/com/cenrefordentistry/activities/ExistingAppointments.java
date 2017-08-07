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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.cenrefordentistry.AppConstants;
import com.cenrefordentistry.R;
import com.cenrefordentistry.RegisterDob;
import com.cenrefordentistry.adapters.ExistingAppointmentsAdapter;
import com.cenrefordentistry.adapters.MessagesAdapter;
import com.cenrefordentistry.daos.AppointmentsDAO;
import com.cenrefordentistry.daos.VoucherDAO;
import com.cenrefordentistry.httpclient.GetExistingAppointments;
import com.cenrefordentistry.models.AppointmentsModel;
import com.cenrefordentistry.models.VoucherModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ExistingAppointments extends AppCompatActivity {

    private RecyclerView exstapRecyclerView;
    ImageView refresh;
    List<AppointmentsModel> existingAppointmentsList = new ArrayList<>();
    AppointmentsDAO appointmentsDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_existing_appointments);
        appointmentsDAO = new AppointmentsDAO(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.existing_appointment_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#007C32"));
        }

        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_arrow_back_white_24dp);
        upArrow.setColorFilter(getResources().getColor(R.color.colorPrimaryDarker), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        refresh = (ImageView) findViewById(R.id.refresh_existing_appointments);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GetExistingAppointments getExistingAppointments = new GetExistingAppointments(ExistingAppointments.this) {
                    @Override
                    public void onResponseReceived(String result) {
                        Log.i("tag","existing appointments"+result);

                        if(result!=null)
                        {
                            Gson gson = new Gson();
                            Type listType = new TypeToken<ArrayList<AppointmentsModel>>() {
                            }.getType();
                            existingAppointmentsList = new Gson().fromJson(result.toString(), listType);
                            Log.i("tag","after convertion treatment"+gson.toJson(existingAppointmentsList));
                            appointmentsDAO = new AppointmentsDAO(ExistingAppointments.this);
                            appointmentsDAO.insert(existingAppointmentsList);
                            exstapRecyclerView.setAdapter(null);
                            ExistingAppointmentsAdapter existingAppointmentsAdapter = new ExistingAppointmentsAdapter(ExistingAppointments.this,existingAppointmentsList);
                            exstapRecyclerView.setAdapter(existingAppointmentsAdapter);

                        }


                    }
                };
                getExistingAppointments.execute(AppConstants.GET_EXISTING_APPOINTMENTS);

            }
        });
        exstapRecyclerView   = (RecyclerView)findViewById(R.id.exstap_recyclerview);
        exstapRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<AppointmentsModel> appointmentsModelList = appointmentsDAO.getExistingAppointmentsData();
        ExistingAppointmentsAdapter existingAppointmentsAdapter = new ExistingAppointmentsAdapter(this,appointmentsModelList);
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
