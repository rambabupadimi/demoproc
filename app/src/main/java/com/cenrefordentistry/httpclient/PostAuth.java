package com.cenrefordentistry.httpclient;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;

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

public abstract class PostAuth extends AsyncTask<String, Void, String>
{
    Activity act;
    JSONObject json,responseJson;
    @Override
    protected String doInBackground(String... urls)
    {
        // TODO Auto-generated method stub
        String result = POST_DATA(urls[0],json);
        //   QKLogs.e(TAG, "doInBackground");
        //   QKLogs.e(TAG, "" + result);

        return result;
    }
    public PostAuth(Activity act)
    {
        this.act = act;
    }

    @Override
    protected void onPreExecute()
    {
        // TODO Auto-generated method stub
        super.onPreExecute();
        // QKLogs.e(TAG, "OnPreExecute");
    }

    public void setJson(JSONObject j)
    {
        json = j;
    }

    // onPostExecute displays the results of the AsyncTask.
    @Override
    protected void onPostExecute(String result)
    {

        //Getting REST response here
        try {
            responseJson = new JSONObject(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        onResponseReceived(responseJson);
    }

    // Making POST request with given values
    public static String POST_DATA(String requestedUrl, JSONObject json)
    {
        //  QKLogs.e(TAG, "" + requestedUrl);
        URL url;
        HttpURLConnection conn = null;
        String response = "";
        try {

            String parameters = json.toString();
            url = new URL(requestedUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setFixedLengthStreamingMode(parameters.getBytes().length);
            conn.setRequestProperty("Content-type", "application/json");
            conn.setRequestProperty("Connection", "close");
            //conn.setConnectTimeout(20000);
            //conn.setReadTimeout(20000);
            if (Build.VERSION.SDK != null && Build.VERSION.SDK_INT > 13) {
                conn.setRequestProperty("Connection", "close");
            }

            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.connect();


            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(parameters);
            writer.flush();
            writer.close();
            os.close();

            //When we get response from the server
            int responseCode=conn.getResponseCode();
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

        //QKLogs.e("POST_DATA=>final_string", "" + response);

        return response;
    }


    public abstract void onResponseReceived(JSONObject result);
}