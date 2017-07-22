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

/**
 * Created by Ramu on 14-07-2017.
 */

public class MyPractice extends Fragment {

    private RecyclerView myPracticeRecyclerview;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_mypractice,container, false);

       myPracticeRecyclerview   = (RecyclerView) view.findViewById(R.id.my_practice_recyclerview);
        myPracticeRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        MyPracticeAdapter myPracticeAdapter = new MyPracticeAdapter(getActivity());
        myPracticeRecyclerview.setAdapter(myPracticeAdapter);


       return view;
    }

}
