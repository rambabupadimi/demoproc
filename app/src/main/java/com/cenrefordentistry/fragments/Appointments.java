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
import com.cenrefordentistry.activities.BookAnAppointmentActivity;
import com.cenrefordentistry.activities.ExistingAppointments;

/**
 * Created by Ramu on 14-07-2017.
 */

public class Appointments extends Fragment {

    Button existingAppointmentButton,bookanappointmentButton,appointmentemergencyButton;
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
                Intent intent = new Intent(getContext(), BookAnAppointmentActivity.class);
                startActivity(intent);
           }
       });

       appointmentemergencyButton = (Button) view.findViewById(R.id.appointment_emergency);
       appointmentemergencyButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
            showAppointmentEmergency();
           }
       });

        return view;
    }


    private void showAppointmentEmergency()
    {
        try {
            final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            final AlertDialog alertDialog = builder.create();
            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.show_appointment_emergency, null);
            Button cancel = (Button) view.findViewById(R.id.ap_emg_cancel);
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
