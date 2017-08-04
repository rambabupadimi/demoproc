package com.cenrefordentistry.httpclient;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.cenrefordentistry.AppConstants;
import com.cenrefordentistry.AppPreferences;
import com.cenrefordentistry.DatabaseFields;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Ramu on 29-07-2017.
 */

public abstract class PostAuthMessages extends AsyncTask<String, Void, String>
{
    Activity act;
    JSONObject json;
    JSONArray responseJson;
    String from="";
    public    AppPreferences appPreferences;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected String doInBackground(String... urls)
    {
        // TODO Auto-generated method stub
        String result = POST_DATA(urls[0],json);
        //   QKLogs.e(TAG, "doInBackground");
        //   QKLogs.e(TAG, "" + result);

        return result;
    }
    public PostAuthMessages(Activity act)
    {
        this.act = act;
        appPreferences =new AppPreferences(act);
    }

    @Override
    protected void onPreExecute()
    {
        // TODO Auto-generated method stub
        super.onPreExecute();
        // QKLogs.e(TAG, "OnPreExecute");
    }

    public void setJson(JSONObject j,String from)
    {
        json = j;
        this.from = from;
    }

    // onPostExecute displays the results of the AsyncTask.
    @Override
    protected void onPostExecute(String result)
    {

        //Getting REST response here
        try {
            responseJson = new JSONArray(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        onResponseReceived(responseJson);
    }

    // Making POST request with given values
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public  String POST_DATA(String requestedUrl,JSONObject json)
    {
        URL url;
        HttpURLConnection conn = null;
        String response = "";


        try {
            String  message_type_id="",
                    message_valid_from_date="",
                    message_valid_until_date="",
                    person_id="";

                    String voucher_type_id="",
                            voucher_valid_from_date="",
                            voucher_valid_until_date="",
                            voucher_treatment_type_id="";


            String parameters="";
            if(json!=null)
            {
                if(from.equalsIgnoreCase("messages")) {
                    message_type_id = json.get("message_type_id").toString();
                    message_valid_from_date = json.get("message_valid_from_date").toString();
                    message_valid_until_date = json.get("message_valid_until_date").toString();
                    person_id = json.get("person_id").toString();
                    parameters = "message_type_id=" + message_type_id + "&message_valid_from_date=" + message_valid_from_date + "&message_valid_until_date=" + message_valid_until_date + "&person_id=" + person_id;
                }
                else if(from.equalsIgnoreCase("vouchers"))
                {
                    voucher_type_id = json.get("voucher_type_id").toString();
                    voucher_valid_from_date = json.get("voucher_valid_from_date").toString();
                    voucher_valid_until_date    =   json.get("voucher_valid_until_date").toString();
                    voucher_treatment_type_id   =   json.get("voucher_treatment_type_id").toString();
                    parameters = "voucher_type_id=" + voucher_type_id + "&voucher_valid_from_date=" + voucher_valid_from_date + "&voucher_valid_until_date=" + voucher_valid_until_date + "&voucher_treatment_type_id=" + voucher_treatment_type_id;

                }
            }
            byte[] postData = parameters.getBytes( StandardCharsets.UTF_8 );
            int postDataLength = postData.length;
            String request = requestedUrl;
            url = new URL( request );
            conn= (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setInstanceFollowRedirects(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Authorization","bearer "+appPreferences.getToken());

                conn.setRequestProperty("charset", "utf-8");
            conn.setRequestProperty("Content-Length", Integer.toString(postDataLength ));
            conn.setUseCaches(false);
            try(DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
                wr.write( postData );
            }


            //When we get response from the server
            int responseCode=conn.getResponseCode();
            StringBuilder result = new StringBuilder();
            if (responseCode == HttpsURLConnection.HTTP_OK || responseCode  == 201)
            {
                String line;
                BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line=br.readLine()) != null) {
                    result.append(line);
                }
                response = result.toString();
            }
        }catch (Exception e) {
        }
        finally {
            if(conn!=null)
            conn.disconnect();
        }
        return response;
    }


    public abstract void onResponseReceived(JSONArray result);
}