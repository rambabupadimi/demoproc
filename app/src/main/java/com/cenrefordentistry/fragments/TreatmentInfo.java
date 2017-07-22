package com.cenrefordentistry.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cenrefordentistry.R;
import com.cenrefordentistry.adapters.MyPracticeAdapter;
import com.cenrefordentistry.adapters.TreatmentInfoAdapter;

/**
 * Created by Ramu on 14-07-2017.
 */

public class TreatmentInfo extends Fragment {
    private  RecyclerView treatmentinfoRecyclerview;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_treatment_info,container, false);

        treatmentinfoRecyclerview   = (RecyclerView) view.findViewById(R.id.ti_recyclerview);
        treatmentinfoRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        TreatmentInfoAdapter treatmentInfoAdapter = new TreatmentInfoAdapter(getActivity());
        treatmentinfoRecyclerview.setAdapter(treatmentInfoAdapter);
        return view;  }

}
