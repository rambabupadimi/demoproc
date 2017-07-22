package com.cenrefordentistry.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.cenrefordentistry.AppConstants;
import com.cenrefordentistry.HomeScreen;
import com.cenrefordentistry.R;

/**
 * Created by Ramu on 14-07-2017.
 */

public class Home extends Fragment implements View.OnClickListener,AppConstants {


    CardView appointments,messages,voucherwallet,myplan;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_home, container, false);

        appointments    =   (CardView) view.findViewById(R.id.appointments_cardview);
        messages        =   (CardView) view.findViewById(R.id.messages_cardview);
        voucherwallet   =   (CardView) view.findViewById(R.id.voucher_cardview);
        myplan          =   (CardView) view.findViewById(R.id.myplan_cardview);

        appointments.setOnClickListener(this);
        messages.setOnClickListener(this);
        voucherwallet.setOnClickListener(this);
        myplan.setOnClickListener(this);

        ImageView settingsIcon = (ImageView) getActivity().findViewById(R.id.toobar_settings);
        settingsIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOptionMenu();
            }
        });
        return view;

    }

    private void showOptionMenu()
    {
        try {
            final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            final AlertDialog alertDialog = builder.create();
            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.option_menu_layout, null);
            Button cancel = (Button) view.findViewById(R.id.menu_cancel);
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                }
            });

            Button showUserid = (Button)view.findViewById(R.id.menu_userid);
            showUserid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                    showResetDialog();
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

    private void showResetDialog(){

        try {
            final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            final AlertDialog alertDialog = builder.create();
            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.show_reset_dialog, null);
            Button cancel = (Button) view.findViewById(R.id.menu_reset_cancel);
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                }
            });

            Button showUserid = (Button)view.findViewById(R.id.menu_reset_data);
            showUserid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                    showResetDataRed();
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

    private void showResetDataRed(){

        try {
            final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            final AlertDialog alertDialog = builder.create();
            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.show_reset_go_dialog, null);
            Button cancel = (Button) view.findViewById(R.id.menu_go_reset_cancel);
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                }
            });

            Button showUserid = (Button)view.findViewById(R.id.menu_go_reset_data);
            showUserid.setOnClickListener(new View.OnClickListener() {
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

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(getActivity(),HomeScreen.class);
        if(v==appointments)
        {
            intent.putExtra("index",AppConstants.APPOINTMENTS_INDEX);
            intent.putExtra("tag",AppConstants.TAG_APPOINTEMNTS);
        }
        else if(v== messages)
        {
            intent.putExtra("index",AppConstants.MESSAGES_INDEX);
            intent.putExtra("tag",AppConstants.TAG_MESSAGES);

        }
        else if(v==myplan)
        {

            intent.putExtra("index",AppConstants.MYPLANS_INDEX);
            intent.putExtra("tag",AppConstants.TAG_MYPLANS);
        }
        else if(v==voucherwallet)
        {
            intent.putExtra("index",AppConstants.VOUCHERWALLET_INDEX);
            intent.putExtra("tag",AppConstants.TAG_VOUCHERWALLET);
        }
        intent.putExtra("from","not_nav");
        startActivity(intent);
    }
}
