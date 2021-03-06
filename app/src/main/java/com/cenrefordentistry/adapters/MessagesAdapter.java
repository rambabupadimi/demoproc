package com.cenrefordentistry.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cenrefordentistry.R;
import com.cenrefordentistry.activities.LongMessageWithCTA;
import com.cenrefordentistry.activities.LongMessageWithNoCTA;
import com.cenrefordentistry.daos.MessagesDAO;
import com.cenrefordentistry.models.MessagesModel;

import java.util.List;

/**
 * Created by Ramu on 15-07-2017.
 */

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.CustomViewHolder>{
    private Context mContext;
    private String TAG = "MyPracticeAdapter.java";
    MessagesDAO messagesDAO;
    List<MessagesModel> messagesModelList;
    public MessagesAdapter(Context context,List<MessagesModel> messagesModelList) {
        this.mContext       =   context;
        this.messagesModelList = messagesModelList;
        messagesDAO =   new MessagesDAO(mContext);
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.messages_adapter,parent,false);
        CustomViewHolder holder=new CustomViewHolder(view);
        return  holder;

    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, final int position) {

        final MessagesModel messagesModel = messagesModelList.get(position);
        holder.title.setText(messagesModel.getMessage_subject());
        holder.description.setText(messagesModel.getMessage_text());
        holder.date.setText(messagesModel.getMessage_valid_until_date().split("T")[0]);

        if(messagesModel.getMessage_is_read()==1)
            holder.messageRead.setBackgroundColor(mContext.getResources().getColor(R.color.colorGray));

        holder.messageRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(messagesModel.getMessage_type_id()==0 || messagesModel.getMessage_type_id()==4)
                {
                    noCallActionPopup(messagesModel,holder);

                }
                else if(messagesModel.getMessage_type_id()==1)
                {
                    popupWithCTA(messagesModel,holder);
                }
                else if(messagesModel.getMessage_type_id()==2)
                {
                    holder.messageRead.setBackgroundColor(mContext.getResources().getColor(R.color.colorGray));
                    messagesDAO.updateMessageIsRead(messagesModel);
                    Intent intent = new Intent(mContext, LongMessageWithNoCTA.class);
                    mContext.startActivity(intent);
                }
                else if(messagesModel.getMessage_type_id()==3)
                {
                    Intent intent = new Intent(mContext, LongMessageWithCTA.class);
                    messagesDAO.updateMessageIsRead(messagesModel);
                    mContext.startActivity(intent);
                    holder.messageRead.setBackgroundColor(mContext.getResources().getColor(R.color.colorGray));

                }
            }
        });


        holder.messageDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessagesDAO messagesDAO = new MessagesDAO(mContext);
                messagesDAO.updateMessageDelete(messagesModel);
                removeAt(position);
            }
        });
    }

    public void removeAt(int position) {
        messagesModelList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, messagesModelList.size());
    }

        private void noCallActionPopup(final MessagesModel messagesModel, final CustomViewHolder holder)
        {
          try {
              final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
              final AlertDialog alertDialog = builder.create();
              LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
              View view = inflater.inflate(R.layout.no_call_action_popup, null);
              Button ok = (Button) view.findViewById(R.id.nap_ok);
              ok.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      alertDialog.dismiss();
                      holder.messageRead.setBackgroundColor(mContext.getResources().getColor(R.color.colorGray));
                      messagesDAO.updateMessageIsRead(messagesModel);

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
      private void popupWithCTA(final MessagesModel messagesModel, final CustomViewHolder holder)
      {
          try {
              final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
              final AlertDialog alertDialog = builder.create();
              LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
              View view = inflater.inflate(R.layout.popup_with_cta, null);
              Button cancel = (Button) view.findViewById(R.id.pwc_cancel);
              cancel.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      alertDialog.dismiss();
                      holder.messageRead.setBackgroundColor(mContext.getResources().getColor(R.color.colorGray));
                      messagesDAO.updateMessageIsRead(messagesModel);

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

    @Override
    public int getItemCount() {
           return (null != messagesModelList ? messagesModelList.size() : 0);
       // return 7;

    }
    public class CustomViewHolder extends RecyclerView.ViewHolder
    {
        Button messageRead,messageDelete;
        TextView title,description,date;
        public CustomViewHolder(View itemView) {
            super(itemView);

            title   = (TextView) itemView.findViewById(R.id.msg_heading);
            description = (TextView) itemView.findViewById(R.id.msg_description);
            date        = (TextView) itemView.findViewById(R.id.msg_date);
            messageDelete   = (Button) itemView.findViewById(R.id.msg_delete);
            messageRead = (Button) itemView.findViewById (R.id.msg_read);

        }
    }



}