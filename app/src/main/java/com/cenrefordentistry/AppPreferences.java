package com.cenrefordentistry;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Dineshgadu on 17-01-2017.
 */

public class AppPreferences {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;

    //
    int PRIVATE_MODE=0;
    private static final String TAG="AppPreferences";
    private static final String PREF_NAME="cfd_app_flags";
    private static final String IS_NOT_FIRST_TIME="is_not_first_time";
    private static final String IMAGE_URI="image_uri";
    private static final String VALID_TIME="valid_time";
    private static final String TOKEN = "token";
    private static final String USERID ="userid";
    // Constructor
    public AppPreferences(Context context)
    {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /*
    * Get Login status, while launching the app
    * */

    public void setIsNotFirstTime(Boolean value)
    {
        String val=value.toString().trim();
        editor.putString(IS_NOT_FIRST_TIME,val);
        editor.commit();
    }

    public String getIsNotFirstTime()
    {
        String flag = pref.getString(IS_NOT_FIRST_TIME, "false");
        return flag;
    }


    public void setUserId(String userId)
    {
        String val = userId.toString().trim();
        editor.putString(USERID,val);
        editor.commit();
    }
    public String getUserId()
    {
        String userid = pref.getString(USERID, "");
        return userid;

    }

    public void setAccessToken(String token)
    {
        String val = token.toString().trim();
        editor.putString(TOKEN,val);
        editor.commit();
    }

    public String getToken()
    {
        String flag = pref.getString(TOKEN, "");
        return flag;
    }

    public void clear()
    {
        editor.clear().commit();
    }
}
