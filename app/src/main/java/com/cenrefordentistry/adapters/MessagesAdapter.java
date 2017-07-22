package com.cenrefordentistry.adapters;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cenrefordentistry.R;

/**
 * Created by Ramu on 15-07-2017.
 */

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.CustomViewHolder>{
    private Context mContext;
    private String TAG = "MyPracticeAdapter.java";
    public MessagesAdapter(Context context) {
        this.mContext       =   context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.messages_adapter,parent,false);
        CustomViewHolder holder=new CustomViewHolder(view);
        return  holder;

    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, final int position) {
        holder.messageRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position==0)
                {
                    noCallActionPopup();
                }
                else if(position ==1)
                {
                    popupWithCTA();
                }
                else if(position==2)
                {

                }


            }
        });
    }


      private void noCallActionPopup()
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
      private void popupWithCTA()
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
        //   return (null != filtered_items ? filtered_items.size() : 0);
        return 5;

    }
    public class CustomViewHolder extends RecyclerView.ViewHolder
    {
        Button messageRead;
        public CustomViewHolder(View itemView) {
            super(itemView);

            messageRead = (Button) itemView.findViewById (R.id.msg_read);
        }
    }



}