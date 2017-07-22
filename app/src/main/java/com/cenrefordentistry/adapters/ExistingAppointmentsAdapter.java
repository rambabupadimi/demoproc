package com.cenrefordentistry.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cenrefordentistry.R;

/**
 * Created by Ramu on 16-07-2017.
 */


public class ExistingAppointmentsAdapter extends RecyclerView.Adapter<ExistingAppointmentsAdapter.CustomViewHolder>{
    private Context mContext;
    private String TAG = "ExistingAppointmentsAdapter.java";
    public ExistingAppointmentsAdapter(Context context) {
        this.mContext       =   context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.existing_appointments_adapter,parent,false);
        CustomViewHolder holder=new CustomViewHolder(view);
        return  holder;

    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        //   return (null != filtered_items ? filtered_items.size() : 0);
        return 5;

    }
    public class CustomViewHolder extends RecyclerView.ViewHolder
    {
        public CustomViewHolder(View itemView) {
            super(itemView);
        }
    }



}