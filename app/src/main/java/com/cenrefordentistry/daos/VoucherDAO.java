package com.cenrefordentistry.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.cenrefordentistry.AppConstants;
import com.cenrefordentistry.DatabaseFields;
import com.cenrefordentistry.models.TreatmentInfoModel;
import com.cenrefordentistry.models.VoucherModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ramu on 29-07-2017.
 */

public class VoucherDAO extends AbstractDAO  implements DatabaseFields{
    public VoucherDAO(Context context) {
        super(context);
    }

private String TAG = "VoucherDAO";

    public long insert(List<VoucherModel> voucherModelList) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            long id = 0;
            if(voucherModelList!=null && voucherModelList.size()>0) {
                for (int i = 0; i < voucherModelList.size(); i++) {
                    VoucherModel voucherModel = voucherModelList.get(i);
                    ContentValues values = new ContentValues();

                    values.put(COLUMN_VOUCHER_ID,voucherModel.getVoucher_id());
                    values.put(COLUMN_VOUCHER_TYPE_ID,voucherModel.getVoucher_type_id());
                    values.put(COLUMN_VOUCHER_TREATMENT_TYPE_ID,voucherModel.getVoucher_treatment_type_id());
                    values.put(COLUMN_VOUCHER_TEXT,voucherModel.getVoucher_text());
                    values.put(COLUMN_VOUCHER_VALID_FROM_DATE,voucherModel.getVoucher_valid_from_date());
                    values.put(COLUMN_VOUCHER_VALID_UNTIL_DATE,voucherModel.getVoucher_valid_until_date());
                    values.put(COLUMN_VOUCHER_FIXED_AMOUNT,voucherModel.getVoucher_fixed_amount());
                    values.put(COLUMN_VOUCHER_PERCENT_AMOUNT,voucherModel.getVoucher_percent_amount());
                    values.put(COLUMN_VOUCHER_CODE,voucherModel.getVoucher_code());
                    values.put(COLUMN_VOUCHER_PRODUCT_CODE,voucherModel.getVoucher_product_code());
                    values.put(COLUMN_VOUCHER_IS_REDEEMED,voucherModel.getVoucher_is_redeemed());
                    values.put(COLUMN_VOUCHER_TITLE,voucherModel.getVoucher_title());
                    values.put(COLUMN_VOUCHER_IS_READ,voucherModel.getVoucher_is_read());
                    values.put(COLUMN_VOUCHER_IS_ACTIVE,voucherModel.getVoucher_is_active());

                    Log.i(TAG, "values insert voucher values" + values);
                    id = db.insert(TABLE_VOUCHERS, null, values);
                    Log.i(TAG, "values insert voucher values result" + id);

                }
            }
            closeDatabase();
            return id;
        } catch (Exception e) {

            closeDatabase();
            return 0;
        }
    }



    public List<VoucherModel> getVoucherData()
    {
        Cursor cursor=null;
        try
        {
            String sql = "select * from " + TABLE_VOUCHERS;
            Log.i(TAG,"sql"+sql);
            SQLiteDatabase db = this.getReadableDatabase();
            cursor = db.rawQuery(sql,null);
            List<VoucherModel> mapList =new ArrayList<>();
            Log.i(TAG,"cursor size"+cursor.getCount());

            if(cursor!=null && cursor.getCount()>0) {
                if (cursor.moveToFirst()) {
                    do {

                        VoucherModel voucherModel = new VoucherModel();
                        voucherModel.setVoucher_id(cursor.getInt(cursor.getColumnIndex(COLUMN_VOUCHER_ID)));
                        voucherModel.setVoucher_type_id(cursor.getInt(cursor.getColumnIndex(COLUMN_VOUCHER_TYPE_ID)));
                        voucherModel.setVoucher_treatment_type_id(cursor.getType(cursor.getColumnIndex(COLUMN_VOUCHER_TREATMENT_TYPE_ID)));
                        voucherModel.setVoucher_text(cursor.getString(cursor.getColumnIndex(COLUMN_VOUCHER_TEXT)));
                        voucherModel.setVoucher_valid_from_date(cursor.getString(cursor.getColumnIndex(COLUMN_VOUCHER_VALID_FROM_DATE)));
                        voucherModel.setVoucher_valid_until_date(cursor.getString(cursor.getColumnIndex(COLUMN_VOUCHER_VALID_UNTIL_DATE)));
                        voucherModel.setVoucher_fixed_amount(cursor.getFloat(cursor.getColumnIndex(COLUMN_VOUCHER_FIXED_AMOUNT)));
                        voucherModel.setVoucher_percent_amount(cursor.getFloat(cursor.getColumnIndex(COLUMN_VOUCHER_PERCENT_AMOUNT)));
                        voucherModel.setVoucher_code(cursor.getString(cursor.getColumnIndex(COLUMN_VOUCHER_CODE)));
                        voucherModel.setVoucher_product_code(cursor.getString(cursor.getColumnIndex(COLUMN_VOUCHER_PRODUCT_CODE)));
                     //   voucherModel.setVoucher_is_redeemed(cursor(cursor.getColumnIndex(COLUMN_VOUCHER_IS_REDEEMED)));
                        voucherModel.setVoucher_title(cursor.getString(cursor.getColumnIndex(COLUMN_VOUCHER_TITLE)));
                        voucherModel.setVoucher_is_read(cursor.getInt(cursor.getColumnIndex(COLUMN_VOUCHER_IS_READ)));
                        voucherModel.setVoucher_is_active(cursor.getInt(cursor.getColumnIndex(COLUMN_VOUCHER_IS_ACTIVE)));

                        mapList.add(voucherModel);
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
