package com.cenrefordentistry.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cenrefordentistry.R;
import com.cenrefordentistry.adapters.MyPracticeAdapter;
import com.cenrefordentistry.daos.MyPracticesDAO;
import com.cenrefordentistry.daos.PersonalDAO;
import com.cenrefordentistry.models.MyPracticesModel;
import com.cenrefordentistry.models.PersonalModel;

import java.util.List;

/**
 * Created by Ramu on 14-07-2017.
 */

public class MyPractice extends Fragment {

    private RecyclerView myPracticeRecyclerview;

    TextView title,description,email,phone;
    ImageView location;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_mypractice,container, false);

       int personId=0;

        PersonalDAO personalDAO = new PersonalDAO(getContext());
        List<PersonalModel> personalModelList =  personalDAO.getPersonalInfo();

        if(personalModelList!=null && personalModelList.size()>0)
        {
            PersonalModel personalModel = personalModelList.get(0);
            personId= personalModel.getPerson_site_id();
        }
        MyPracticesDAO myPracticesDAO = new MyPracticesDAO(getContext());
        final MyPracticesModel myPracticesModel = myPracticesDAO.getMyPractice(personId);


        title = (TextView) view.findViewById(R.id.cfd_east_fiton_title_home);
        description = (TextView) view.findViewById(R.id.cfd_east_fiton_address_home);
        email = (TextView) view.findViewById(R.id.cfd_east_fiton_email_home);
        phone = (TextView) view.findViewById(R.id.cfd_east_fiton_phone_home);
        location    = (ImageView) view.findViewById(R.id.location_icon_home);

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    String uri = "http://maps.google.com/maps?q=loc:" + myPracticesModel.getSite_latitude() + "," + myPracticesModel.getSite_longitude() + " (" + myPracticesModel.getSite_text() + ")";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                    startActivity(intent);
                }catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        });
        title.setText(myPracticesModel.getSite_text());
        description.setText(myPracticesModel.getSite_address());
        email.setText(myPracticesModel.getSite_email_address());
        phone.setText(myPracticesModel.getSite_phone_number());


        List<MyPracticesModel> myPracticesModelList = myPracticesDAO.getMyPraticesData();
        myPracticeRecyclerview   = (RecyclerView) view.findViewById(R.id.my_practice_recyclerview);
        myPracticeRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        MyPracticeAdapter myPracticeAdapter = new MyPracticeAdapter(getActivity(),myPracticesModelList);
        myPracticeRecyclerview.setAdapter(myPracticeAdapter);


       return view;
    }

}
