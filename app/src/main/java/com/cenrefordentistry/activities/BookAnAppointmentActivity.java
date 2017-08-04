package com.cenrefordentistry.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.cenrefordentistry.HomeScreen;
import com.cenrefordentistry.R;

public class BookAnAppointmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_an_appointment);
        appointmentStage1();
    }


    private void appointmentStage2()
    {
        try {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            final AlertDialog alertDialog = builder.create();
            LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.appointment_stage_2, null);

            Button mydentist = (Button) view.findViewById(R.id.ap_my_dentist);
            Button anydentist =(Button) view.findViewById(R.id.ap_any_dentist);

            Button cancel = (Button) view.findViewById(R.id.ap_stage2_cancel);
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                    finish();
                }
            });
            mydentist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                 appointmentStage3();
                }
            });
            anydentist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    appointmentStage3();
                }
            });

            alertDialog.setView(view);
            alertDialog.show();
            alertDialog.setCancelable(false);
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    private  void appointmentStage1()
    {
        try {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            final AlertDialog alertDialog = builder.create();
            LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.appointment_stage_1, null);

            Button checkup = (Button) view.findViewById(R.id.ap_checkup);
            Button hygiene =(Button) view.findViewById(R.id.ap_hygiene);
            Button emergency = (Button)view.findViewById(R.id.ap_emergency);

            checkup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                    appointmentStage2();
                }
            });

            hygiene.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                    appointmentStage2();
                }
            });
            emergency.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                    appointmentStage2();
                }
            });

            Button cancel = (Button) view.findViewById(R.id.ap_cancel);
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                    finish();
                }
            });


            alertDialog.setView(view);
            alertDialog.show();
            alertDialog.setCancelable(false);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void appointmentStage3()
    {
        try {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            final AlertDialog alertDialog = builder.create();
            LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.appointment_stage_3, null);

            Button anyTime = (Button) view.findViewById(R.id.ap_stg3_anytime);

            anyTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                    Intent intent = new Intent(BookAnAppointmentActivity.this, AppointmentCalenderView.class);
                    startActivity(intent);
                }
            });

            Button cancel = (Button) view.findViewById(R.id.ap_stg3_cancel);
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                    finish();
                }
            });

            alertDialog.setView(view);
            alertDialog.show();
            alertDialog.setCancelable(false);
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }



}
