package com.cenrefordentistry.daos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.cenrefordentistry.AppConstants;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Ramu on 26-07-2017.
 */
public class AbstractDAO implements AppConstants {
    public String currentTimeStamp;
    private String TAG = "AbstractDAO";
    public Context context = null;

    public long user_id;

    public AbstractDAO(Context context) {
        this.context = context;
        Date myDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
        calendar.setTime(myDate);
        Date time = calendar.getTime();
        SimpleDateFormat outputFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        currentTimeStamp = outputFmt.format(time);

    }

    public void closeDatabase() {
        try {
            DatabaseManager.getInstance(context).closeDatabase();
        } catch (Exception e) {

        }
    }

    public SQLiteDatabase getReadableDatabase() {
        return DatabaseManager.getInstance(context).openDatabase();
    }

    public SQLiteDatabase getWritableDatabase() {
        return DatabaseManager.getInstance(context).openDatabase();
    }

    public void delete() {
        DatabaseManager.getInstance(context).deleteDatabase();
    }
}
