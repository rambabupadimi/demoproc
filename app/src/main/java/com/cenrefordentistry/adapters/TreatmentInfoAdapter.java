package com.cenrefordentistry.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cenrefordentistry.R;
import com.cenrefordentistry.activities.TreatmentInfoFullDetails;

/**
 * Created by Ramu on 16-07-2017.
 */

public class TreatmentInfoAdapter extends RecyclerView.Adapter<TreatmentInfoAdapter.CustomViewHolder>{
    private Context mContext;
    private String TAG = "MyPracticeAdapter.java";
    public TreatmentInfoAdapter(Context context) {
        this.mContext       =   context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.treatment_info_adapter,parent,false);
        CustomViewHolder holder=new CustomViewHolder(view);
        return  holder;
    }
    @Override
    public void onBindViewHolder(final CustomViewHolder holder, int position) {
        holder.tinfCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, TreatmentInfoFullDetails.class);
                mContext.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        //   return (null != filtered_items ? filtered_items.size() : 0);
        return 5;

    }
    public class CustomViewHolder extends RecyclerView.ViewHolder
    {
        CardView tinfCardview;
        public CustomViewHolder(View itemView) {
            super(itemView);

            tinfCardview = (CardView) itemView.findViewById(R.id.tinf_cardview);
        }
    }



}