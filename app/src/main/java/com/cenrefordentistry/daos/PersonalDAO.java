package com.cenrefordentistry.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.cenrefordentistry.DatabaseFields;
import com.cenrefordentistry.models.PersonalModel;
import com.cenrefordentistry.models.TreatmentInfoModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ramu on 28-07-2017.
 */

public class PersonalDAO extends AbstractDAO implements DatabaseFields {

    private String TAG ="PersonalDAO";
    public PersonalDAO(Context context) {
        super(context);
    }


    public long insert(PersonalModel personalModel) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_PERSON_ID,        personalModel.getPerson_id());
            values.put(COLUMN_PERSON_SITE_ID,   personalModel.getPerson_site_id());
            values.put(COLUMN_PERSON_PLAN_STATUS_ID, personalModel.getPerson_plan_status_id());
            values.put(COLUMN_PERSON_PLAN_RENEWAL_DATE,  personalModel.getPerson_plan_renewal_date());
            values.put(COLUMN_PERSON_CREATED_DATE,  personalModel.getPerson_created_date());

            values.put(COLUMN_PERSON_LAST_SEEN_DATE,  personalModel.getPerson_last_seen_date());
            values.put(COLUMN_PERSON_FIRST_NAME,  personalModel.getPerson_first_name());
            values.put(COLUMN_WELCOME_MESSAGE_1,personalModel.getGeneral_welcome_message_1());
            values.put(COLUMN_WELCOME_MESSAGE_2,personalModel.getGeneral_welcome_message_2());
            values.put(COLUMN_REFEREL_SUCCESS_COUNT,personalModel.getReferal_success_count());


            Log.i(TAG,"values insert"+values);
            long id = db.insert(TABLE_PERSON, null, values);
            closeDatabase();
            return id;
        } catch (Exception e) {

            closeDatabase();
            return 0;
        }
    }



    public List<PersonalModel> getPersonalInfo()
    {
        try
        {
            String sql = "select * from " + TABLE_PERSON;
            Log.i(TAG,"sql"+sql);
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(sql,null);
            List<PersonalModel> mapList =new ArrayList<>();
            if (cursor!=null && cursor.moveToFirst()) {
                Log.i(TAG,"cursor size"+cursor.getCount());

                do {

                    PersonalModel personalModel = new PersonalModel();
                    personalModel.setPerson_id(cursor.getInt(cursor.getColumnIndex(COLUMN_PERSON_ID)));
                    personalModel.setPerson_site_id(cursor.getInt(cursor.getColumnIndex(COLUMN_PERSON_SITE_ID)));
                    personalModel.setPerson_plan_status_id(cursor.getInt(cursor.getColumnIndex(COLUMN_PERSON_PLAN_STATUS_ID)));
                    personalModel.setPerson_plan_renewal_date(cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_PLAN_RENEWAL_DATE)));

                    personalModel.setPerson_created_date(cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_CREATED_DATE)));
                    personalModel.setPerson_last_seen_date(cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_LAST_SEEN_DATE)));
                    personalModel.setPerson_first_name(cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_FIRST_NAME)));
                    personalModel.setGeneral_welcome_message_1(cursor.getString(cursor.getColumnIndex(COLUMN_WELCOME_MESSAGE_1)));
                    personalModel.setGeneral_welcome_message_2(cursor.getString(cursor.getColumnIndex(COLUMN_WELCOME_MESSAGE_2)));
                    personalModel.setReferal_success_count(cursor.getInt(cursor.getColumnIndex(COLUMN_REFEREL_SUCCESS_COUNT)));

                    mapList.add(personalModel);
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
