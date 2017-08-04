package com.cenrefordentistry.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.cenrefordentistry.DatabaseFields;
import com.cenrefordentistry.models.MyPracticesModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ramu on 28-07-2017.
 */

public class MyPracticesDAO extends AbstractDAO implements DatabaseFields {
    private String TAG = "MyPracticesDAO";
    public MyPracticesDAO(Context context) {
        super(context);
    }



    public long insert(List<MyPracticesModel> myPracticesModelList) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            long id=0;
            if(myPracticesModelList!=null && myPracticesModelList.size()>0) {
                for(int i=0;i<myPracticesModelList.size();i++) {

                    MyPracticesModel myPractice = myPracticesModelList.get(i);
                    ContentValues values = new ContentValues();
                    values.put(COLUMN_SITE_ID, myPractice.getSite_id());
                    values.put(COLUMN_SITE_TEXT, myPractice.getSite_text());
                    values.put(COLUMN_SITE_LONGITUDE, myPractice.getSite_longitude());
                    values.put(COLUMN_SITE_LATITUDE, myPractice.getSite_latitude());
                    values.put(COLUMN_SITE_ADDRESS, myPractice.getSite_address());
                    values.put(COLUMN_SITE_EMAIL_ADDRESS, myPractice.getSite_email_address());
                    values.put(COLUMN_SITE_PHONE_NUMBER, myPractice.getSite_phone_number());
                    values.put(COLUMN_SITE_POST_CODE, myPractice.getSite_postcode());
                    Log.i(TAG, "values insert mypractices" + values);
                    id = db.insert(TABLE_PRACTICES, null, values);
                }
            }
            closeDatabase();
            return id;
        } catch (Exception e) {

            closeDatabase();
            return 0;
        }
    }


    public MyPracticesModel getMyPractice(int siteid)
    {
        try
        {
            String sql = "select * from " + TABLE_PRACTICES+" where "+COLUMN_SITE_ID+"="+siteid;
            Log.i(TAG,"sql"+sql);
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(sql,null);
            MyPracticesModel myPracticesModel = new MyPracticesModel();
            if (cursor!=null && cursor.moveToFirst()) {
                    Log.i(TAG,"cursor size"+cursor.getCount());
                    myPracticesModel.setSite_id(cursor.getInt(cursor.getColumnIndex(COLUMN_SITE_ID)));
                    myPracticesModel.setSite_text(cursor.getString(cursor.getColumnIndex(COLUMN_SITE_TEXT)));
                    myPracticesModel.setSite_longitude(cursor.getString(cursor.getColumnIndex(COLUMN_SITE_LONGITUDE)));
                    myPracticesModel.setSite_latitude(cursor.getString(cursor.getColumnIndex(COLUMN_SITE_LATITUDE)));
                    myPracticesModel.setSite_address(cursor.getString(cursor.getColumnIndex(COLUMN_SITE_ADDRESS)));
                    myPracticesModel.setSite_email_address(cursor.getString(cursor.getColumnIndex(COLUMN_SITE_EMAIL_ADDRESS)));
                    myPracticesModel.setSite_phone_number(cursor.getString(cursor.getColumnIndex(COLUMN_SITE_PHONE_NUMBER)));
                    myPracticesModel.setSite_postcode(cursor.getString(cursor.getColumnIndex(COLUMN_SITE_POST_CODE)));
            }
            cursor.close();
            closeDatabase();
            return myPracticesModel;
        }catch (Exception e) {

            closeDatabase();
            return null;
        }

    }


    public List<MyPracticesModel> getMyPraticesData()
    {
        try
        {
            String sql = "select * from " + TABLE_PRACTICES;
            Log.i(TAG,"sql"+sql);
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(sql,null);
            List<MyPracticesModel> mapList =new ArrayList<>();
            if (cursor!=null && cursor.moveToFirst()) {
                Log.i(TAG,"cursor size"+cursor.getCount());

                do {

                    MyPracticesModel myPracticesModel = new MyPracticesModel();
                    myPracticesModel.setSite_id(cursor.getInt(cursor.getColumnIndex(COLUMN_SITE_ID)));
                    myPracticesModel.setSite_text(cursor.getString(cursor.getColumnIndex(COLUMN_SITE_TEXT)));
                    myPracticesModel.setSite_longitude(cursor.getString(cursor.getColumnIndex(COLUMN_SITE_LONGITUDE)));
                    myPracticesModel.setSite_latitude(cursor.getString(cursor.getColumnIndex(COLUMN_SITE_LATITUDE)));
                    myPracticesModel.setSite_address(cursor.getString(cursor.getColumnIndex(COLUMN_SITE_ADDRESS)));
                    myPracticesModel.setSite_email_address(cursor.getString(cursor.getColumnIndex(COLUMN_SITE_EMAIL_ADDRESS)));
                    myPracticesModel.setSite_phone_number(cursor.getString(cursor.getColumnIndex(COLUMN_SITE_PHONE_NUMBER)));
                    myPracticesModel.setSite_postcode(cursor.getString(cursor.getColumnIndex(COLUMN_SITE_POST_CODE)));
                    mapList.add(myPracticesModel);
                }while (cursor.moveToNext());
            }
            cursor.close();
            closeDatabase();
            return mapList;
        }catch (Exception e) {

            closeDatabase();
            return null;
        }

    }

}
