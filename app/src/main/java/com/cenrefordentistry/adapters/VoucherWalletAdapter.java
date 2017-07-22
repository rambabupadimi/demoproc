package com.cenrefordentistry.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.cenrefordentistry.LandingPage;
import com.cenrefordentistry.R;
import com.cenrefordentistry.RegisterDob;

/**
 * Created by Ramu on 15-07-2017.
 */

public class VoucherWalletAdapter extends RecyclerView.Adapter<VoucherWalletAdapter.CustomViewHolder>{
    private Context mContext;
    private String TAG = "VoucherWalletAdapter.java";
    public VoucherWalletAdapter(Context context) {
        this.mContext       =   context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.voucher_wallet_adapter,parent,false);
        CustomViewHolder holder=new CustomViewHolder(view);
        return  holder;

    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, int position) {
        holder.vwLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  showVoucherDialog();
            }
         });

        holder.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showVoucherDialog();
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
        RelativeLayout vwLayout;
        Button detail;
        public CustomViewHolder(View itemView) {
            super(itemView);
            vwLayout = (RelativeLayout) itemView.findViewById(R.id.vw_parent_layout);
            detail  = (Button) itemView.findViewById(R.id.vw_wallet_detail);
        }
    }


    private void showVoucherDialog()
    {
        try {
            final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            final AlertDialog alertDialog = builder.create();
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.voucher_dialog, null);
            Button cancel = (Button) view.findViewById(R.id.vw_cancel);
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