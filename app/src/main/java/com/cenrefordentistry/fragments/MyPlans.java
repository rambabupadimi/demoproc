package com.cenrefordentistry.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cenrefordentistry.AppConstants;
import com.cenrefordentistry.R;
import com.cenrefordentistry.adapters.VoucherWalletAdapter;

/**
 * Created by Ramu on 14-07-2017.
 */

public class MyPlans extends Fragment implements AppConstants {

    private TextView myplan1,myplan2;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_myplans,container, false);

        myplan1 = (TextView) view.findViewById(R.id.my_plan_message1);
        myplan2 = (TextView) view.findViewById(R.id.my_plan_message2);

        myplan1.setText(Html.fromHtml(AppConstants.MY_PLAN_MESSAGE1));
        myplan2.setText(Html.fromHtml(AppConstants.MY_PLAN_MESSAGE2));
        return view;


    }

}
