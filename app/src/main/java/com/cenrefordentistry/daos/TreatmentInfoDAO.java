package com.cenrefordentistry.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.cenrefordentistry.DatabaseFields;
import com.cenrefordentistry.models.TreatmentInfoModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ramu on 28-07-2017.
 */

public class TreatmentInfoDAO extends AbstractDAO implements DatabaseFields {

    private String TAG ="TreatmentInfoDAO";
    public TreatmentInfoDAO(Context context) {
        super(context);
    }


    public long insert(List<TreatmentInfoModel> treatmentInfoModelList) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            long id = 0;
            if(treatmentInfoModelList!=null && treatmentInfoModelList.size()>0) {
                for (int i = 0; i < treatmentInfoModelList.size(); i++) {
                    TreatmentInfoModel treatmentInfoModel = treatmentInfoModelList.get(i);
                    ContentValues values = new ContentValues();
                    values.put(COLUMN_TREATMENT_TYPE_ID, treatmentInfoModel.getTreatment_type_id());
                    values.put(COLUMN_TREATMENT_TYPE_TEXT, treatmentInfoModel.getTreatment_type_text());
                    values.put(COLUMN_TREATMENT_TYPE_CODE, treatmentInfoModel.getTreatment_type_code());
                    values.put(COLUMN_TREATMENT_TYPE_COLOR, treatmentInfoModel.getTreatment_type_colour());
                    Log.i(TAG, "values insert treatmentinfo" + values);
                    id = db.insert(TABLE_TREATMENT_TYPES, null, values);
                    Log.i(TAG, "values insert treatmentinfo result" + id);

                }
            }
            closeDatabase();
            return id;
        } catch (Exception e) {

            closeDatabase();
            return 0;
        }
    }



    public List<TreatmentInfoModel> getTreatmentInfoData()
    {Cursor cursor=null;
        try
        {
            String sql = "select * from " + TABLE_TREATMENT_TYPES;
            Log.i(TAG,"sql"+sql);
            SQLiteDatabase db = this.getReadableDatabase();
            cursor = db.rawQuery(sql,null);
            List<TreatmentInfoModel> mapList =new ArrayList<>();
            Log.i(TAG,"cursor size"+cursor.getCount());

            if(cursor!=null && cursor.getCount()>0) {
                if (cursor.moveToFirst()) {
                    do {
                        TreatmentInfoModel treatmentInfoModel = new TreatmentInfoModel();

                        treatmentInfoModel.setTreatment_type_id(cursor.getInt(cursor.getColumnIndex(COLUMN_TREATMENT_TYPE_ID)));
                        treatmentInfoModel.setTreatment_type_text(cursor.getString(cursor.getColumnIndex(COLUMN_TREATMENT_TYPE_TEXT)));
                        treatmentInfoModel.setTreatment_type_code(cursor.getString(cursor.getColumnIndex(COLUMN_TREATMENT_TYPE_CODE)));
                        treatmentInfoModel.setTreatment_type_colour(cursor.getString(cursor.getColumnIndex(COLUMN_TREATMENT_TYPE_COLOR)));

                        mapList.add(treatmentInfoModel);
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
