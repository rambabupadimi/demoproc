package com.cenrefordentistry.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cenrefordentistry.R;

/**
 * Created by Ramu on 15-07-2017.
 */

public class MyPracticeAdapter extends RecyclerView.Adapter<MyPracticeAdapter.CustomViewHolder>{
    private Context mContext;
    private String TAG = "MyPracticeAdapter.java";
    public MyPracticeAdapter(Context context) {
        this.mContext       =   context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_practice_adapter,parent,false);
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