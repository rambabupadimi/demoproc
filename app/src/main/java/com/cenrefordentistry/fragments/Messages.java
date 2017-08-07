package com.cenrefordentistry.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cenrefordentistry.R;
import com.cenrefordentistry.adapters.MessagesAdapter;
import com.cenrefordentistry.adapters.MyPracticeAdapter;
import com.cenrefordentistry.daos.MessagesDAO;
import com.cenrefordentistry.models.MessagesModel;

import java.util.List;

/**
 * Created by Ramu on 14-07-2017.
 */

public class Messages extends Fragment {

    private RecyclerView msgRecyclerview;

    private Button selectAll,deleteAll;
    MessagesAdapter messagesAdapter;
    MessagesDAO messagesDAO;

    LinearLayout noItems;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_messages,container, false);

        noItems = (LinearLayout) view.findViewById(R.id.no_items);
        messagesDAO = new MessagesDAO(getContext());
        final List<MessagesModel> messagesModelList = messagesDAO.getMessagesData();

        selectAll   =   (Button) view.findViewById(R.id.msg_read_all);
        deleteAll   =   (Button) view.findViewById(R.id.msg_delete_all);

        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupDeleteAll(messagesModelList,"deleteall");
            }
        });

        selectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupDeleteAll(messagesModelList,"selectall");
            }
        });




        msgRecyclerview   = (RecyclerView) view.findViewById(R.id.msg_recyclerview);
        msgRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        messagesAdapter = new MessagesAdapter(getActivity(),messagesModelList);
        msgRecyclerview.setAdapter(messagesAdapter);

        if(messagesModelList!=null && messagesModelList.size()==0)
        {
            noItems.setVisibility(View.VISIBLE);
            selectAll.setVisibility(View.GONE);
            deleteAll.setVisibility(View.GONE);
            msgRecyclerview.setVisibility(View.GONE);
        }
        else
        {
            noItems.setVisibility(View.GONE);
            selectAll.setVisibility(View.VISIBLE);
            deleteAll.setVisibility(View.VISIBLE);
            msgRecyclerview.setVisibility(View.VISIBLE);

        }


        return view;
    }


    private void popupDeleteAll(final List<MessagesModel> messagesModelList, final String from)
    {
        try {
            final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            final AlertDialog alertDialog = builder.create();
            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.popup_delete_all, null);
            Button ok = (Button) view.findViewById(R.id.popup_ok);
            Button cancel = (Button) view.findViewById(R.id.popup_cancel);
            final TextView message  = (TextView) view.findViewById(R.id.popup_message);

            if(from.toString().equalsIgnoreCase("selectall"))
            {
                message.setText("Are you sure select all?");
            }
            if(from.toString().equalsIgnoreCase("deleteall"))
            {
                message.setText("Are you sure delete all?");
            }
            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                    if(from.toString().equalsIgnoreCase("selectall"))
                        messagesDAO.updateMessageIsReadAll(messagesModelList);
                    else if(from.toString().equalsIgnoreCase("deleteall"))
                        messagesDAO.updateMessageDeleteAll(messagesModelList);
                    msgRecyclerview.setAdapter(null);
                    List<MessagesModel>   messagesModelList = messagesDAO.getMessagesData();
                    messagesAdapter = new MessagesAdapter(getActivity(),messagesModelList);
                    msgRecyclerview.setAdapter(messagesAdapter);

                }
            });

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
