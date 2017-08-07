package com.cenrefordentistry.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cenrefordentistry.R;
import com.cenrefordentistry.models.AppointmentsModel;

import java.util.List;

/**
 * Created by Ramu on 16-07-2017.
 */


public class ExistingAppointmentsAdapter extends RecyclerView.Adapter<ExistingAppointmentsAdapter.CustomViewHolder>{
    private Context mContext;
    private String TAG = "ExistingAppointmentsAdapter.java";

    List<AppointmentsModel> appointmentsModelList;
    public ExistingAppointmentsAdapter(Context context, List<AppointmentsModel> appointmentsModelList) {
        this.mContext       =   context;
        this.appointmentsModelList = appointmentsModelList;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.existing_appointments_adapter,parent,false);

        CustomViewHolder holder=new CustomViewHolder(view);
        return  holder;

    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, int position) {
            AppointmentsModel appointmentsModel = appointmentsModelList.get(position);
            holder.appointmentDate.setText(appointmentsModel.getAppointment_datetime());
            holder.appointmentText.setText(appointmentsModel.getAppointment_provider_text());
            if(appointmentsModel.getAppointment_status_id()==1)
                holder.appointmentStatus.setText("Confirmed");

    }

    @Override
    public int getItemCount() {
           return (null != appointmentsModelList ? appointmentsModelList.size() : 0);
    }
    public class CustomViewHolder extends RecyclerView.ViewHolder
    {

        TextView appointmentDate,appointmentText,appointmentStatus;
        public CustomViewHolder(View itemView) {
            super(itemView);
            appointmentDate = (TextView) itemView.findViewById(R.id.appointment_date);
            appointmentText = (TextView) itemView.findViewById(R.id.appointment_text);
            appointmentStatus = (TextView) itemView.findViewById(R.id.appointment_status);



        }
    }



}