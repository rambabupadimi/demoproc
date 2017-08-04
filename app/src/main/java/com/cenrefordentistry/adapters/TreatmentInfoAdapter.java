package com.cenrefordentistry.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cenrefordentistry.AppConstants;
import com.cenrefordentistry.R;
import com.cenrefordentistry.activities.TreatmentInfoFullDetails;
import com.cenrefordentistry.models.TreatmentInfoModel;

import java.util.List;

/**
 * Created by Ramu on 16-07-2017.
 */

public class TreatmentInfoAdapter extends RecyclerView.Adapter<TreatmentInfoAdapter.CustomViewHolder> implements AppConstants{
    private Context mContext;
    private String TAG = "MyPracticeAdapter.java";
    List<TreatmentInfoModel> treatmentInfoModelList;
    public TreatmentInfoAdapter(Context context,List<TreatmentInfoModel> treatmentInfoModelList) {
        this.mContext       =   context;
        this.treatmentInfoModelList = treatmentInfoModelList;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.treatment_info_adapter,parent,false);
        CustomViewHolder holder=new CustomViewHolder(view);
        return  holder;
    }
    @Override
    public void onBindViewHolder(final CustomViewHolder holder, int position) {

        final TreatmentInfoModel treatmentInfoModel = treatmentInfoModelList.get(position);
        holder.title.setText(treatmentInfoModel.getTreatment_type_text());
        holder.code.setText(treatmentInfoModel.getTreatment_type_code());
        String color =treatmentInfoModel.getTreatment_type_colour().toString();
        if(color.equalsIgnoreCase("LGC"))
        {
            holder.code.setBackgroundColor(mContext.getResources().getColor(R.color.colorPrimaryDarker));
        }
        else if(color.equalsIgnoreCase("BLUE"))
        {
            holder.code.setBackgroundColor(mContext.getResources().getColor(R.color.colorBlue));
        }else if(color.equalsIgnoreCase("DPINK"))
        {
            holder.code.setBackgroundColor(mContext.getResources().getColor(R.color.colorPink));
        }else if(color.equalsIgnoreCase("VOILET"))
        {
            holder.code.setBackgroundColor(mContext.getResources().getColor(R.color.colorPrimary));
        }else if(color.equalsIgnoreCase("LGC"))
        {
            holder.code.setBackgroundColor(mContext.getResources().getColor(R.color.colorRed));
        }

        if(treatmentInfoModel.getTreatment_type_code().toString().equalsIgnoreCase("ANY") ||
                treatmentInfoModel.getTreatment_type_code().toString().equalsIgnoreCase("P"))
        {
            holder.tinfCardview.setVisibility(View.GONE);
        }

        holder.tinfCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, TreatmentInfoFullDetails.class);
                String title = treatmentInfoModel.getTreatment_type_text();
                if(title.toString().equalsIgnoreCase("Bridges and Partial Dentures")) {
                    title = "bridgesandpartialdentures";
                }else if(title.toString().equalsIgnoreCase("Children's Dental Treatment"))
                {
                    title ="childrendental";
                }
                else if(title.toString().equalsIgnoreCase("Dental Crowns"))
                {
                    title ="dentalcrown";
                }
                else if(title.toString().equalsIgnoreCase("Dental Implant"))
                {
                    title ="dentalimplant";
                }
                else if(title.toString().equalsIgnoreCase("Dentures"))
                {
                    title ="dentures";
                }
                else if(title.toString().equalsIgnoreCase("Drill Free Dentistry"))
                {
                    title ="drillfreedentistry";
                }
                else if(title.toString().equalsIgnoreCase("Facial Aesthetics"))
                {
                    title ="facialaesthetics";
                }
                else if(title.toString().equalsIgnoreCase("Checkups"))
                {
                    title ="generalcheckup";
                }
                else if(title.toString().equalsIgnoreCase("Orthodontics"))
                {
                    title ="orthodontics";
                }
                else if(title.toString().equalsIgnoreCase("Root Canal Therapy"))
                {
                    title ="rootcanal";
                }
                else if(title.toString().equalsIgnoreCase("Scale and Polish"))
                {
                    title ="scaleanpolish";
                }
                else if(title.toString().equalsIgnoreCase("Teeth Whitening"))
                {
                    title ="teethwhitening";
                }
                else if(title.toString().equalsIgnoreCase("Tooth Extraction"))
                {
                    title ="toothextraction";
                }
                else if(title.toString().equalsIgnoreCase("Veneers"))
                {
                    title ="veneers";
                }

                else if(title.toString().equalsIgnoreCase("White Fillings"))
                {
                    title ="whitefillings";
                }
                else if(title.toString().equalsIgnoreCase("Products"))
                {
                    title ="products";
                }
                intent.putExtra(AppConstants.TITLE,title+"_title");
                intent.putExtra(AppConstants.DESCRIPTION,title+"_desc");
                intent.putExtra(AppConstants.COLOR_CODE,treatmentInfoModel.getTreatment_type_colour());
                intent.putExtra(AppConstants.IMAGE,treatmentInfoModel.getTreatment_type_code());
                mContext.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
          return (null != treatmentInfoModelList ? treatmentInfoModelList.size() : 0);

    }
    public class CustomViewHolder extends RecyclerView.ViewHolder
    {
        CardView tinfCardview;
        TextView title,code;
        public CustomViewHolder(View itemView) {
            super(itemView);

            tinfCardview =  (CardView) itemView.findViewById(R.id.tinf_cardview);
            title       =   (TextView) itemView.findViewById(R.id.ti_title);
            code        =   (TextView) itemView.findViewById(R.id.ti_box);
        }
    }



}