package com.cenrefordentistry.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cenrefordentistry.R;
import com.cenrefordentistry.adapters.TreatmentInfoAdapter;

/**
 * Created by Ramu on 27-07-2017.
 */

public class RequestCallBack extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_request_call_back,container, false);
        return view;  }

}
