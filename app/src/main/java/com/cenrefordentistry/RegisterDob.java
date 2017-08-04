package com.cenrefordentistry;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cenrefordentistry.daos.DatabaseManager;
import com.cenrefordentistry.daos.MessagesDAO;
import com.cenrefordentistry.daos.MyPracticesDAO;
import com.cenrefordentistry.daos.PersonalDAO;
import com.cenrefordentistry.daos.TreatmentInfoDAO;
import com.cenrefordentistry.daos.VoucherDAO;
import com.cenrefordentistry.httpclient.AccessToken;
import com.cenrefordentistry.httpclient.GetAuth;
import com.cenrefordentistry.httpclient.GetUserDetails;
import com.cenrefordentistry.httpclient.PostAuthMessages;
import com.cenrefordentistry.models.MessagesModel;
import com.cenrefordentistry.models.MyPracticesModel;
import com.cenrefordentistry.models.PersonalModel;
import com.cenrefordentistry.models.TreatmentInfoModel;
import com.cenrefordentistry.models.VoucherModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RegisterDob extends AppCompatActivity implements AppConstants{

    Button rdbContinue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_dob);
        rdbContinue = (Button) findViewById(R.id.rdb_continue);
        final Gson  gson = new Gson();
        rdbContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                @SuppressLint("StaticFieldLeak") AccessToken accessToken = new AccessToken(RegisterDob.this) {
                    @Override
                    public void onResponseReceived(JSONObject result) {
                        Log.i("tag","result"+result);
                        if(result!=null && result.length()>0) {

                            AppPreferences appPreferences = new AppPreferences(RegisterDob.this);
                            try {
                                String accessToken = result.get("access_token").toString();
                                appPreferences.setAccessToken(accessToken);

                                final GetUserDetails postToken = new GetUserDetails(RegisterDob.this) {
                                    @Override
                                    public void onResponseReceived(JSONObject result) {
                                        if(result!=null)
                                        {
                                            Log.i("tag","result final is"+result);


                                            PersonalModel personalModel = gson.fromJson(String.valueOf(result),PersonalModel.class);
                                            Log.i("tag","after user details"+gson.toJson(personalModel));

                                            PersonalDAO personalDAO = new PersonalDAO(RegisterDob.this);
                                            personalDAO.insert(personalModel);

                                            try
                                            {
                                                // Treatments
                                                GetAuth treatmentTypes = new GetAuth(RegisterDob.this) {
                                                    @Override
                                                    public void onResponseReceived(String result) {
                                                        Log.i("tag","result of treatment types"+result);
                                                        if(result!=null) {
                                                            Type listType = new TypeToken<ArrayList<TreatmentInfoModel>>() {
                                                            }.getType();
                                                            List<TreatmentInfoModel> treatmentInfoModelList = new Gson().fromJson(result, listType);
                                                            Log.i("tag","after convertion treatment"+gson.toJson(treatmentInfoModelList));

                                                            TreatmentInfoDAO treatmentInfoDAO = new TreatmentInfoDAO(RegisterDob.this);
                                                            treatmentInfoDAO.insert(treatmentInfoModelList);
                                                        }
                                                    }
                                                };
                                                treatmentTypes.execute(AppConstants.GET_TREATMENT_TYPES);
                                                // MyPractices
                                                GetAuth practices = new GetAuth(RegisterDob.this) {
                                                    @Override
                                                    public void onResponseReceived(String result) {
                                                        Log.i("tag","result of practices"+result);

                                                        if(result!=null) {
                                                            Type listType = new TypeToken<ArrayList<MyPracticesModel>>() {
                                                            }.getType();
                                                            List<MyPracticesModel> myPracticesModelList = new Gson().fromJson(result, listType);
                                                            Log.i("tag","after convertion mypractices"+gson.toJson(myPracticesModelList));

                                                            MyPracticesDAO myPracticesDAO = new MyPracticesDAO(RegisterDob.this);
                                                            myPracticesDAO.insert(myPracticesModelList);
                                                        }

                                                    }
                                                };
                                                practices.execute(AppConstants.GET_PRACTICES);

                                                //messages
                                                PostAuthMessages postAuthMessages = new PostAuthMessages(RegisterDob.this) {
                                                    @Override
                                                    public void onResponseReceived(JSONArray result) {
                                                        Log.i("tag","response of messages"+result);
                                                        if(result!=null)
                                                        {
                                                            Type listType = new TypeToken<ArrayList<MessagesModel>>() {
                                                            }.getType();
                                                            List<MessagesModel> messagesModelList = new Gson().fromJson(result.toString(), listType);
                                                            Log.i("tag","after convertion treatment"+gson.toJson(messagesModelList));

                                                            MessagesDAO messagesDAO = new MessagesDAO(RegisterDob.this);
                                                            messagesDAO.insert(messagesModelList);
                                                        }
                                                    }
                                                };
                                                JSONObject jsonObject = new JSONObject();
                                                jsonObject.put("message_type_id","-1");
                                                jsonObject.put("message_valid_from_date","2017-05-31");
                                                jsonObject.put("message_valid_until_date","2017-06-29");
                                                PersonalDAO personalDAO1 = new PersonalDAO(RegisterDob.this);
                                                List<PersonalModel> personalModelList = personalDAO1.getPersonalInfo();
                                                if(personalModelList!=null && personalModelList.size()>0)
                                                {
                                                    PersonalModel personalModel1 = personalModelList.get(0);
                                                    jsonObject.put("person_id",""+personalModel1.getPerson_id());
                                                    jsonObject.put("Content-Type","application/x-www-form-urlencoded");
                                                    postAuthMessages.setJson(jsonObject,"messages");
                                                    postAuthMessages.execute(AppConstants.GET_MESSAGES);
                                                }

                                                //vouchers

                                                PostAuthMessages postAuthMessages1 = new PostAuthMessages(RegisterDob.this) {
                                                    @Override
                                                    public void onResponseReceived(JSONArray result) {
                                                        Log.i("tag","response of vouchers"+result);


                                                        if(result!=null)
                                                        {
                                                            Type listType = new TypeToken<ArrayList<VoucherModel>>() {
                                                            }.getType();
                                                            List<VoucherModel> voucherModelList = new Gson().fromJson(result.toString(), listType);
                                                            Log.i("tag","after convertion treatment"+gson.toJson(voucherModelList));
                                                            VoucherDAO voucherDAO = new VoucherDAO(RegisterDob.this);
                                                            voucherDAO.insert(voucherModelList);
                                                        }


                                                    }
                                                };
                                                JSONObject jsonObject1 = new JSONObject();
                                                jsonObject1.put("voucher_type_id","-1");
                                                jsonObject1.put("voucher_valid_from_date","2017-05-31");
                                                jsonObject1.put("voucher_valid_until_date","2017-06-29");
                                                jsonObject1.put("voucher_treatment_type_id","-1");
                                                postAuthMessages1.setJson(jsonObject1,"vouchers");
                                                postAuthMessages1.execute(AppConstants.GET_VOUCHERS);




                                            }catch (Exception e)
                                            {
                                                e.printStackTrace();
                                            }

                                            Intent intent = new Intent(RegisterDob.this, HomeScreen.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            startActivity(intent);



                                        }
                                    }
                                };
                                postToken.execute(AppConstants.GET_PERSON);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        else
                        {
                            Toast.makeText(RegisterDob.this,"Please Try Again...",Toast.LENGTH_LONG).show();
                        }
                    }
                };
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put(AppConstants.USERNAME,"C4037C15-800E-437F-B177-02E14885D07C");
                    jsonObject.put(AppConstants.PASSWORD,"24111985");
                    jsonObject.put(AppConstants.GRANT_TYPE,"password");
                    accessToken.setJson(jsonObject);
                    accessToken.execute(AppConstants.GET_TOKEN_API);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });
    }

}
