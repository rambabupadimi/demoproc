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
import android.widget.TextView;
import android.widget.Toast;

import com.cenrefordentistry.LandingPage;
import com.cenrefordentistry.R;
import com.cenrefordentistry.RegisterDob;
import com.cenrefordentistry.models.VoucherModel;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Ramu on 15-07-2017.
 */

public class VoucherWalletAdapter extends RecyclerView.Adapter<VoucherWalletAdapter.CustomViewHolder>{
    private Context mContext;
    private String TAG = "VoucherWalletAdapter.java";

    List<VoucherModel> voucherModelList;
    public VoucherWalletAdapter(Context context,List<VoucherModel> voucherModelList) {
        this.mContext       =   context;
        this.voucherModelList   =   voucherModelList;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.voucher_wallet_adapter,parent,false);
        CustomViewHolder holder=new CustomViewHolder(view);
        return  holder;

    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, int position) {


        final VoucherModel voucherModel = voucherModelList.get(position);
        holder.voucher_title.setText(voucherModel.getVoucher_title());
        holder.voucher_description.setText(voucherModel.getVoucher_text());
        holder.voucher_expires.setText(voucherModel.getVoucher_valid_until_date().split("T")[0]);
        holder.voucher_press_for_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"click me",Toast.LENGTH_LONG).show();
                showVoucherDialog(voucherModel);
            }
        });

    }
    @Override
    public int getItemCount() {
        return (null != voucherModelList ? voucherModelList.size() : 0);

    }
    public class CustomViewHolder extends RecyclerView.ViewHolder
    {
        RelativeLayout vwLayout;
        Button detail;
        TextView voucher_title,voucher_description,voucher_expires,voucher_press_for_detail;

        public CustomViewHolder(View itemView) {
            super(itemView);
            vwLayout                    =   (RelativeLayout) itemView.findViewById(R.id.vw_parent_layout);
            detail                      =   (Button) itemView.findViewById(R.id.vw_wallet_detail);
            voucher_title               =   (TextView) itemView.findViewById(R.id.vw_heading);
            voucher_description         =   (TextView) itemView.findViewById(R.id.vw_description);
            voucher_expires             =   (TextView) itemView.findViewById(R.id.vw_expires);
            voucher_press_for_detail    =   (Button) itemView.findViewById(R.id.vw_wallet_detail);
        }
    }


    private void showVoucherDialog(VoucherModel voucherModel)
    {
        try {
            final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            final AlertDialog alertDialog = builder.create();
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.voucher_dialog, null);
            TextView title = (TextView) view.findViewById(R.id.vw_dialog_title);
            TextView description = (TextView) view.findViewById(R.id.vw_dialog_text);
            TextView code = (TextView) view.findViewById(R.id.vw_dialog_code);

            title.setText(voucherModel.getVoucher_title());
            description.setText(voucherModel.getVoucher_text());
            code.setText(voucherModel.getVoucher_code());

            Button cancel = (Button) view.findViewById(R.id.vw_dialog_cancel);
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