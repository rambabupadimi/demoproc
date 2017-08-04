package com.cenrefordentistry.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.cenrefordentistry.DatabaseFields;
import com.cenrefordentistry.models.MessagesModel;
import com.cenrefordentistry.models.TreatmentInfoModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ramu on 29-07-2017.
 */

public class MessagesDAO extends AbstractDAO implements DatabaseFields {
    public MessagesDAO(Context context) {
        super(context);
    }
    private String TAG ="MessagesDAO";


    public long insert(List<MessagesModel> messagesModelList) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            long id = 0;
            if(messagesModelList!=null && messagesModelList.size()>0) {
                for (int i = 0; i < messagesModelList.size(); i++) {

                    MessagesModel messagesModel = messagesModelList.get(i);
                    ContentValues values = new ContentValues();

                    values.put(COLUMN_MESSAGE_ID, messagesModel.getMessage_id());
                    values.put(COLUMN_MESSAGE_TYPE_ID, messagesModel.getMessage_type_id());
                    values.put(COLUMN_MESSAGE_TEXT, messagesModel.getMessage_text());
                    values.put(COLUMN_MESSAGE_VALID_FROM_DATE, messagesModel.getMessage_valid_from_date());
                    values.put(COLUMN_MESSAGE_VALID_UNTIL_DATE, messagesModel.getMessage_valid_until_date());
                    values.put(COLUMN_MESSAGE_IS_PROXIMITY, messagesModel.getMessage_is_proximity());
                    values.put(COLUMN_MESSAGE_SUBJECT, messagesModel.getMessage_subject());
                    values.put(COLUMN_MESSAGE_IS_ACTIVE,messagesModel.getMessage_is_active());
                    values.put(COLUMN_MESSAGE_IS_READ,messagesModel.getMessage_is_read());

                    Log.i(TAG, "values insert messages" + values);
                    id = db.insert(TABLE_MESSAGES, null, values);
                    Log.i(TAG, "values insert messages result" + id);

                }
            }
            closeDatabase();
            return id;
        } catch (Exception e) {

            closeDatabase();
            return 0;
        }
    }



    public List<MessagesModel> getMessagesData()
    {Cursor cursor=null;
        try
        {
            String sql = "select * from " + TABLE_MESSAGES;
            Log.i(TAG,"sql"+sql);
            SQLiteDatabase db = this.getReadableDatabase();
            cursor = db.rawQuery(sql,null);
            List<MessagesModel> mapList =new ArrayList<>();
            Log.i(TAG,"cursor size"+cursor.getCount());

            if(cursor!=null && cursor.getCount()>0) {
                if (cursor.moveToFirst()) {
                    do {
                        MessagesModel       messagesModel = new MessagesModel();
                        messagesModel.setMessage_id(cursor.getInt(cursor.getColumnIndex(COLUMN_MESSAGE_ID)));
                        messagesModel.setMessage_type_id(cursor.getInt(cursor.getColumnIndex(COLUMN_MESSAGE_TYPE_ID)));
                        messagesModel.setMessage_text(cursor.getString(cursor.getColumnIndex(COLUMN_MESSAGE_TEXT)));
                        messagesModel.setMessage_valid_from_date(cursor.getString(cursor.getColumnIndex(COLUMN_MESSAGE_VALID_FROM_DATE)));
                        messagesModel.setMessage_valid_until_date(cursor.getString(cursor.getColumnIndex(COLUMN_MESSAGE_VALID_UNTIL_DATE)));
                        //messagesModel.setMessage_is_proximity(cursor.getInt(cursor.getColumnIndex(COLUMN_MESSAGE_IS_PROXIMITY)));
                        messagesModel.setMessage_subject(cursor.getString(cursor.getColumnIndex(COLUMN_MESSAGE_SUBJECT)));
                        messagesModel.setMessage_is_active(cursor.getInt(cursor.getColumnIndex(COLUMN_MESSAGE_IS_ACTIVE)));
                        messagesModel.setMessage_is_read(cursor.getInt(cursor.getColumnIndex(COLUMN_MESSAGE_IS_READ)));
                        mapList.add(messagesModel);
                    } while (cursor.moveToNext());
                }
            }
            return mapList;
        }catch (Exception e) {

            closeDatabase();
            return null;
        }
        finally {
            if(cursor!=null)
                cursor.close();
            closeDatabase();

        }

    }



}
