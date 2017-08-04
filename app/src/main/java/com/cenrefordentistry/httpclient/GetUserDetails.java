package com.cenrefordentistry.httpclient;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import com.cenrefordentistry.AppPreferences;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Ramu on 23-07-2017.
 */

public abstract class GetUserDetails extends AsyncTask<String, Void, String>
{
    Activity act;
    JSONObject json,responseJson;
   public AppPreferences appPreferences;
    @Override
    protected String doInBackground(String... urls)
    {
        // TODO Auto-generated method stub
        String result = POST_DATA(urls[0]);

        return result;
    }
    public GetUserDetails(Activity act)
    {
        appPreferences = new AppPreferences(act);
        this.act = act;
    }

    @Override
    protected void onPreExecute()
    {
        // TODO Auto-generated method stub
        super.onPreExecute();
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
           if(result!=null) {
                Log.i("tag","post token response"+result);
               responseJson = new JSONObject(result);
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
        onResponseReceived(responseJson);
    }

    // Making POST request with given values
    public  String POST_DATA(String requestedUrl)
    {
        //  QKLogs.e(TAG, "" + requestedUrl);
        URL url;
        HttpURLConnection conn = null;
        String response = "";
        try {

            Log.i("app token","token"+appPreferences.getToken());

            url = new URL(requestedUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Accept","application/json");
            conn.setRequestProperty("Accept-Language","en-gb");
            conn.setRequestProperty("Audience","C4037C15-800E-437F-B177-02E14885D07C");
            conn.setRequestProperty("Authorization","bearer "+appPreferences.getToken());

            //conn.setConnectTimeout(20000);
            //conn.setReadTimeout(20000);
            if (Build.VERSION.SDK != null && Build.VERSION.SDK_INT > 13) {
                conn.setRequestProperty("Connection", "close");
            }

           // conn.setDoInput(true);
           // conn.setDoOutput(true);
            conn.connect();


            //When we get response from the server
            int responseCode=conn.getResponseCode();
            Log.i("tag","response code"+responseCode);
            StringBuilder result = new StringBuilder();
            if (responseCode == HttpsURLConnection.HTTP_OK || responseCode  == 201)
            {
                try {
                    String line;
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    while ((line = br.readLine()) != null) {
                        result.append(line);
                    }
                    response = result.toString();
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
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