package com.cenrefordentistry.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cenrefordentistry.R;
import com.cenrefordentistry.models.MyPracticesModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Ramu on 15-07-2017.
 */

public class MyPracticeAdapter extends RecyclerView.Adapter<MyPracticeAdapter.CustomViewHolder>{
    private Context mContext;
    private String TAG = "MyPracticeAdapter.java";
    List<MyPracticesModel> myPracticesModelList =new ArrayList<>();

    public MyPracticeAdapter(Context context,List<MyPracticesModel> myPracticesModelList) {
        this.mContext       =   context;
        this.myPracticesModelList = myPracticesModelList;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_practice_adapter,parent,false);
        CustomViewHolder holder=new CustomViewHolder(view);
        return  holder;

    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, int position) {

        final MyPracticesModel myPracticesModel = myPracticesModelList.get(position);
        holder.siteText.setText(myPracticesModel.getSite_text());
        holder.siteEmail.setText(myPracticesModel.getSite_email_address());
        holder.sitePhoneNumber.setText(myPracticesModel.getSite_phone_number());
        holder.siteAddres.setText(myPracticesModel.getSite_address());

        holder.locate_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    String uri = "http://maps.google.com/maps?q=loc:" + myPracticesModel.getSite_latitude() + "," + myPracticesModel.getSite_longitude() + " (" + myPracticesModel.getSite_text() + ")";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                    mContext.startActivity(intent);
                }catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        });
    }


    @Override
    public int getItemCount() {
           return (null != myPracticesModelList ? myPracticesModelList.size() : 0);
    }
    public class CustomViewHolder extends RecyclerView.ViewHolder
    {
        TextView siteText,siteAddres,siteEmail,sitePhoneNumber;
        Button locate_me;
        public CustomViewHolder(View itemView) {
            super(itemView);
            siteText    = (TextView) itemView.findViewById(R.id.cfd_east_fiton_title_home);
            siteAddres  = (TextView) itemView.findViewById(R.id.cfd_east_fiton_address_home);
            siteEmail   = (TextView) itemView.findViewById(R.id.cfd_east_fiton_email_home);
            sitePhoneNumber = (TextView) itemView.findViewById(R.id.cfd_east_fiton_phone_home);
            locate_me    =  (Button) itemView.findViewById(R.id.locate_me);
        }
    }



}