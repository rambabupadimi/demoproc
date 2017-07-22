package com.cenrefordentistry.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cenrefordentistry.R;
import com.cenrefordentistry.adapters.MessagesAdapter;
import com.cenrefordentistry.adapters.MyPracticeAdapter;

/**
 * Created by Ramu on 14-07-2017.
 */

public class Messages extends Fragment {

    private RecyclerView msgRecyclerview;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_messages,container, false);
        msgRecyclerview   = (RecyclerView) view.findViewById(R.id.msg_recyclerview);
        msgRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        MessagesAdapter messagesAdapter = new MessagesAdapter(getActivity());
        msgRecyclerview.setAdapter(messagesAdapter);
        return view;
    }

}
