package com.cenrefordentistry.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cenrefordentistry.LandingPage;
import com.cenrefordentistry.R;
import com.cenrefordentistry.RegisterDob;
import com.cenrefordentistry.activities.ExistingAppointments;

/**
 * Created by Ramu on 14-07-2017.
 */

public class Appointments extends Fragment {

    Button existingAppointmentButton,bookanappointmentButton;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_appointments,container, false);

        existingAppointmentButton = (Button) view.findViewById(R.id.existing_appointment_button);
        existingAppointmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ExistingAppointments.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });


       bookanappointmentButton = (Button) view.findViewById(R.id.book_an_appointment_button);
       bookanappointmentButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                appointmentStage1();
           }
       });

        return view;
    }



    private void appointmentStage2()
    {
        try {
            final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            final AlertDialog alertDialog = builder.create();
            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.appointment_stage_2, null);

            Button mydentist = (Button) view.findViewById(R.id.ap_my_dentist);
            Button anydentist =(Button) view.findViewById(R.id.ap_any_dentist);

            Button cancel = (Button) view.findViewById(R.id.ap_stage2_cancel);
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
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
            final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            final AlertDialog alertDialog = builder.create();
            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
