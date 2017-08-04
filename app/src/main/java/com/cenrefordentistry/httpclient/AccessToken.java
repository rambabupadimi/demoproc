package com.cenrefordentistry.httpclient;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.cenrefordentistry.AppConstants;

import org.json.JSONException;
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
 * Created by Ramu on 23-07-2017.
 */

public abstract class AccessToken extends AsyncTask<String, Void, String> implements AppConstants
{
    public  JSONObject json, responseJson;
    Activity act;
    private  String TAG = "AccessToken";
    private ProgressDialog mDialog;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected String doInBackground(String... urls)
    {
        // TODO Auto-generated method stub
        String result = POST_DATA(urls[0]);
        return result;
    }
    public AccessToken(Activity act)

    {
        this.act = act;
    }

    @Override
    protected void onPreExecute()
    {
        // TODO Auto-generated method stub
        super.onPreExecute();
        mDialog = new ProgressDialog(act);
        mDialog.setTitle("Please Wait...");
        mDialog.show();

     }

    public void setJson(JSONObject j)
    {
        json = j;
    }

    // onPostExecute displays the results of the AsyncTask.
    @Override
    protected void onPostExecute(String result)
    {
        try {
            Log.i("tag","result of resp"+result);
            if(result!=null&& result.length()>0) {
                mDialog.dismiss();
                responseJson = new JSONObject(result);
                onResponseReceived(responseJson);

            }else
            {
                onResponseReceived(responseJson);
                mDialog.dismiss();
            }
            //mDialog.dismiss();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    // Making POST request with given values
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public  String POST_DATA(String requestedUrl)
    {
        URL url;
        HttpURLConnection conn = null;
        String response = "";


        try {
          String username="",password="",granttype="";
            if(json!=null)
            {
                username = json.get(AppConstants.USERNAME).toString();
                password = json.get(AppConstants.PASSWORD).toString();
                granttype ="password";
            }
            String parameters = "username="+username+"&password="+password+"&grant_type="+granttype;

            byte[] postData = parameters.getBytes( StandardCharsets.UTF_8 );
            int postDataLength = postData.length;
            String request = requestedUrl;
            url = new URL( request );
            conn= (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setInstanceFollowRedirects(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
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
            conn.disconnect();
        }
        return response;
    }


    public abstract void onResponseReceived(JSONObject result);
}