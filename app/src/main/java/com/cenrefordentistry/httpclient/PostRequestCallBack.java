package com.cenrefordentistry.httpclient;

/**
 * Created by Ramu on 05-08-2017.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.cenrefordentistry.AppPreferences;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;
/**
 * Created by Ramu on 29-07-2017.
 */

public abstract class PostRequestCallBack extends AsyncTask<String, Void, String>
{
    Activity act;
    JSONObject json;
    JSONArray responseJson;
    String from="";

    private ProgressDialog mDialog;
    public AppPreferences appPreferences;

    public PostRequestCallBack(Activity act)
    {
        this.act = act;
        appPreferences =new AppPreferences(act);
    }

    public void setJson(JSONObject j)
    {
        json = j;
    }


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

    @Override
    protected void onPreExecute()
    {
        // TODO Auto-generated method stub
        super.onPreExecute();
        // QKLogs.e(TAG, "OnPreExecute");
        mDialog = new ProgressDialog(act);
        mDialog.setTitle("Please Wait...");
        mDialog.show();

    }

    // onPostExecute displays the results of the AsyncTask.
    @Override
    protected void onPostExecute(String result)
    {

        //Getting REST response here

        try {
            if(result!=null) {

                mDialog.dismiss();
                responseJson = new JSONArray(result);
            }
            else
            {
                mDialog.dismiss();
            }
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
            String  requestor_email="",
                    treatment_type_id="",
                    additional_comments="";


            String parameters="";
            if(json!=null)
            {
                requestor_email = json.get("requestor_email").toString();
                treatment_type_id = json.get("treatment_type_id").toString();
                additional_comments = json.get("additional_comments").toString();
                    parameters = "requestor_email=" + requestor_email + "&treatment_type_id=" + treatment_type_id + "&additional_comments=" + additional_comments;
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
