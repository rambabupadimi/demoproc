package com.cenrefordentistry.httpclient;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

import com.cenrefordentistry.AppPreferences;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Ramu on 23-07-2017.
 */
public abstract class GetAuth extends AsyncTask<String, Void , String> {
    Context context;
    public static String accessToken, getAccessTokenType;
String ourresult;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    public GetAuth(Context context)
    {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {

        // QKLogs.i("coming do in bacground", "coming do in background");
        Integer result = 0;
        HttpURLConnection urlConnection;
        try {
            URL url = new URL(params[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            AppPreferences appPreferences = new AppPreferences(context);
            urlConnection.setRequestProperty("Authorization","bearer "+appPreferences.getToken());
            int statusCode = urlConnection.getResponseCode();
            // 200 represents HTTP OK
            if (statusCode == 201 || statusCode == 200) {
                BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = r.readLine()) != null) {
                    response.append(line);
                }
                ourresult = response.toString();
                return ourresult;
            }
        } catch (Exception e) {
        }
        return ourresult; //"Failed to fetch data!";
    }


    @Override
    protected void onPostExecute(String result) {
        try {

            onResponseReceived(result);
            //   mDialog.dismiss();
            //    setResult(json);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            //    mDialog.dismiss();
        }
    }

    public abstract void onResponseReceived(String result);
}
