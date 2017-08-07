package com.cenrefordentistry.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cenrefordentistry.AppConstants;
import com.cenrefordentistry.R;
import com.cenrefordentistry.adapters.TreatmentInfoAdapter;
import com.cenrefordentistry.httpclient.PostRequestCallBack;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Ramu on 27-07-2017.
 */

public class RequestCallBack extends Fragment {

    EditText requestPhone,requestComments,requestEmail;
    Button requestCallBackButton;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_request_call_back,container, false);

        requestPhone    =   (EditText) view.findViewById(R.id.request_phone_number);
        requestComments =   (EditText) view.findViewById(R.id.request_comments);
        requestCallBackButton   = (Button) view.findViewById(R.id.request_call_back);
        requestEmail    =   (EditText)view.findViewById(R.id.request_email);
        requestCallBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String comments     =   requestComments.getText().toString();
                String phone="",email="";
                if(requestPhone.getText().toString().trim().length()>9)
                {
                    phone        =   requestPhone.getText().toString();

                }

                if(requestEmail.getText().toString().length()>0)
                {
                    email = requestEmail.getText().toString();
                }

                if(requestEmail.getText().toString().trim().length()==0 &&
                        requestPhone.getText().toString().trim().length() ==0)
                {

                    Toast.makeText(getContext(),"Please enter any one",Toast.LENGTH_LONG).show();
                    return;
                }
                JSONObject jsonObject = new JSONObject();

                if(requestComments.getText().toString().trim().length()>0)
                {
                    try {
                        jsonObject.put("requestor_email",email );
                        jsonObject.put("requestor_telephone_number",phone);
                        jsonObject.put("treatment_type_id", "-1");
                        jsonObject.put("additional_comments",comments );
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    PostRequestCallBack postRequestCallBack = new PostRequestCallBack(getActivity()) {
                        @Override
                        public void onResponseReceived(JSONArray result) {

                            Log.i("tag","result is"+result);

                        }
                    };
                    postRequestCallBack.setJson(jsonObject);
                    postRequestCallBack.execute(AppConstants.GET_REQUEST_CALL_BACK);
                }
                else
                {
                    Toast.makeText(getContext(),"Please enter comments",Toast.LENGTH_LONG).show();

                }

            }
        });

        return view;
    }

}
