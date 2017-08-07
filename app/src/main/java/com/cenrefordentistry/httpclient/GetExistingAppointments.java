package com.cenrefordentistry.httpclient;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.cenrefordentistry.AppPreferences;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Ramu on 06-08-2017.
 */

public abstract class GetExistingAppointments extends AsyncTask<String, Void , String> {
    Context context;
    String ourresult;
    private ProgressDialog mDialog;



    public GetExistingAppointments(Context context)
    {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mDialog = new ProgressDialog(context);
        mDialog.setTitle("Please Wait...");
        mDialog.show();

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
               mDialog.dismiss();
            //    setResult(json);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
                mDialog.dismiss();
        }
    }

    public abstract void onResponseReceived(String result);
}
