package com.cenrefordentistry.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.cenrefordentistry.DatabaseFields;
import com.cenrefordentistry.models.AppointmentsModel;
import com.cenrefordentistry.models.MessagesModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ramu on 06-08-2017.
 */

public class AppointmentsDAO extends AbstractDAO implements DatabaseFields {
    public AppointmentsDAO(Context context) {
        super(context);
    }

    private String TAG = "AppointmentsDAO";


    public long insert(List<AppointmentsModel> appointmentsModelList) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            long id = 0;
            if (appointmentsModelList != null && appointmentsModelList.size() > 0) {
                for (int i = 0; i < appointmentsModelList.size(); i++) {

                    AppointmentsModel appointmentsModel = appointmentsModelList.get(i);

                   if(!checkAppointmentAlreadyExistsOrNot(appointmentsModel.getAppointment_id())) {
                       ContentValues values = new ContentValues();
                       values.put(COLUMN_APPOINTMENT_ID, appointmentsModel.getAppointment_id());
                       values.put(COLUMN_APPOINTMENT_PROVIDER_TEXT, appointmentsModel.getAppointment_provider_text());
                       values.put(COLUMN_APPOINTMENT_PROVIDER_ID, appointmentsModel.getAppointment_provider_id());
                       values.put(COLUMN_APPOINTMENT_SITE_ID, appointmentsModel.getAppointment_site_id());
                       values.put(COLUMN_APPOINTMENT_PERSON_ID, appointmentsModel.getAppointment_person_id());
                       values.put(COLUMN_APPOINTMENT_TYPE_ID, appointmentsModel.getAppointment_type_id());
                       values.put(COLUMN_APPOINTMENT_REMOTE_ID, appointmentsModel.getAppointment_remote_id());
                       values.put(COLUMN_APPOINTMENT_STATUS_ID, appointmentsModel.getAppointment_status_id());
                       values.put(COLUMN_APPOINTMENT_DATETIME, appointmentsModel.getAppointment_datetime());
                       values.put(COLUMN_APPOINTMENT_BOOKING_PRICE, appointmentsModel.getAppointment_booking_price());

                       Log.i(TAG, "values insert messages" + values);
                       id = db.insert(TABLE_APPOINTMENTS, null, values);
                       Log.i(TAG, "values insert messages result" + id);
                   }
                }
            }
            closeDatabase();
            return id;
        } catch (Exception e) {

            closeDatabase();
            return 0;
        }
    }


    public boolean checkAppointmentAlreadyExistsOrNot(int appointmentid)
    {
        Cursor cursor = null;
        try {
            String sql = "select * from " + TABLE_APPOINTMENTS+" where "+COLUMN_APPOINTMENT_ID+" = "+appointmentid;
            Log.i(TAG, "sql" + sql);
            SQLiteDatabase db = this.getReadableDatabase();
            cursor = db.rawQuery(sql, null);
            Log.i(TAG, "cursor size" + cursor.getCount());

            if (cursor != null && cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {

            closeDatabase();
            return false;
        } finally {
            if (cursor != null)
                cursor.close();
            closeDatabase();

        }

    }

    public List<AppointmentsModel> getExistingAppointmentsData() {
        Cursor cursor = null;
        try {
            String sql = "select * from " + TABLE_APPOINTMENTS;
            Log.i(TAG, "sql" + sql);
            SQLiteDatabase db = this.getReadableDatabase();
            cursor = db.rawQuery(sql, null);
            List<AppointmentsModel> mapList = new ArrayList<>();
            Log.i(TAG, "cursor size" + cursor.getCount());

            if (cursor != null && cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    do {
                        AppointmentsModel appointmentsModel = new AppointmentsModel();
                        appointmentsModel.setAppointment_id(cursor.getInt(cursor.getColumnIndex(COLUMN_APPOINTMENT_ID)));
                        appointmentsModel.setAppointment_person_id(cursor.getInt(cursor.getColumnIndex(COLUMN_APPOINTMENT_PERSON_ID)));

                        appointmentsModel.setAppointment_provider_id(cursor.getInt(cursor.getColumnIndex(COLUMN_APPOINTMENT_PROVIDER_ID)));
                        appointmentsModel.setAppointment_provider_text(cursor.getString(cursor.getColumnIndex(COLUMN_APPOINTMENT_PROVIDER_TEXT)));
                        appointmentsModel.setAppointment_booking_price(cursor.getFloat(cursor.getColumnIndex(COLUMN_APPOINTMENT_BOOKING_PRICE)));

                        appointmentsModel.setAppointment_type_id(cursor.getInt(cursor.getColumnIndex(COLUMN_APPOINTMENT_TYPE_ID)));
                        appointmentsModel.setAppointment_site_id(cursor.getInt(cursor.getColumnIndex(COLUMN_APPOINTMENT_SITE_ID)));
                        appointmentsModel.setAppointment_remote_id(cursor.getInt(cursor.getColumnIndex(COLUMN_APPOINTMENT_REMOTE_ID)));

                        appointmentsModel.setAppointment_datetime(cursor.getString(cursor.getColumnIndex(COLUMN_APPOINTMENT_DATETIME)));
                        appointmentsModel.setAppointment_status_id(cursor.getInt(cursor.getColumnIndex(COLUMN_APPOINTMENT_STATUS_ID)));
                        mapList.add(appointmentsModel);
                    } while (cursor.moveToNext());
                }
            }
            return mapList;
        } catch (Exception e) {

            closeDatabase();
            return null;
        } finally {
            if (cursor != null)
                cursor.close();
            closeDatabase();

        }

    }

}