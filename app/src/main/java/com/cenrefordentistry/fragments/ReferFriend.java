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

import com.cenrefordentistry.HomeScreen;
import com.cenrefordentistry.R;

import java.sql.Ref;

/**
 * Created by Ramu on 14-07-2017.
 */

public class ReferFriend extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_refer_friend,container, false);
        showReferFriendDialog();
        return view;
    }


    private void showReferFriendDialog()
    {
        try {
            final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            final AlertDialog alertDialog = builder.create();
            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.refer_friend_dialog, null);
            Button cancel = (Button) view.findViewById(R.id.rfd_cancel);
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                    Intent intent = new Intent(getActivity(),HomeScreen.class);
                    startActivity(intent);
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
