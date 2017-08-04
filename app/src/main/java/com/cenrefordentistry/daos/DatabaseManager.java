package com.cenrefordentistry.daos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.cenrefordentistry.AppConstants;
import com.cenrefordentistry.DatabaseFields;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Ramu on 26-07-2017.
 */

public class DatabaseManager extends SQLiteOpenHelper implements AppConstants , DatabaseFields {
    private static DatabaseManager instance;
    private static SQLiteOpenHelper mDatabaseHelper;
    private static String TAG = "DatabaseManager";
    private SQLiteDatabase mDatabase;
    private Context context;
    private AtomicInteger mOpenCounter = new AtomicInteger();

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {



        String TABLE_APPOINTMENTS_QUERY = "CREATE TABLE IF NOT EXISTS  "+DatabaseFields.TABLE_APPOINTMENTS+"  ( " +
                " appointment_local_id      INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " appointment_server_id     INTEGER, "+
                " appointment_provider_id   INTEGER, "+
                " appointment_provider_text TEXT, "+
                " appointment_site_id       INTEGER, "+
                " appointment_person_id     INTEGER, "+
                " appointment_type_id       INTEGER, "+
                " appointment_remote_id     INTEGER, "+
                " appointment_status_id     INTEGER, "+
                " appointment_datetime      DATE, "+
                " appointment_booking_charge FLOAT, "+
                " is_read                   INTEGER )";


        String TABLE_MESSAGES_QUERY = "CREATE TABLE IF NOT EXISTS  "+DatabaseFields.TABLE_MESSAGES+"  ( " +
                "message_local_id	INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "message_id	        INTEGER, "+
                "message_type_id	INTEGER, "+
                "message_text  	    TEXT, "+
                "message_valid_from_date	DATE,   "+
                "message_valid_until_date	DATE,"+
                "message_is_proximity	INTEGER, "+
                "message_subject 	TEXT, "+
                "message_is_active	        INTEGER NOT NULL,"+
                "message_is_read	        INTEGER NOT NULL )";

        String TABLE_PERSON_QUERY = "CREATE TABLE IF NOT EXISTS  "+DatabaseFields.TABLE_PERSON+"  ( " +

                "person_id                  INTEGER, "+
                "person_site_id             INTEGER, "+
                "person_plan_status_id      INTEGER, "+
                "person_plan_renewal_date   DATE, "+
                "person_created_date        DATE, "+
                "person_last_seen_date      DATE, "+
                "person_first_name          TEXT, "+
                "general_welcome_message_1  TEXT, "+
                "general_welcome_message_2  TEXT, "+
                "referal_success_count      INTEGER )";




        String TABLE_PRACTICES_QUERY = "CREATE TABLE IF NOT EXISTS  "+DatabaseFields.TABLE_PRACTICES+"  ( " +
	            "site_id	        INTEGER PRIMARY KEY AUTOINCREMENT, "+
	            "site_text	        TEXT,   "+
	            "site_longitude	    TEXT, "+
	            "site_latitude	    TEXT, "+
	            "site_address	    TEXT, "+
	            "site_email_address	    TEXT, "+
	            "site_phone_number	    TEXT, "+
	            "site_post_code	        TEXT, "+
	            "is_home	            INTEGER )";

        String TABLE_PROVIDER_TYPES_QUERY = "CREATE TABLE IF NOT EXISTS  "+DatabaseFields.TABLE_PROVIDER_TYPES+"  ( " +
                "provider_type_id   INTEGER, "+
                "provider_type_text TEXT )";

        String TABLE_PROVIDERS_QUERY = "CREATE TABLE IF NOT EXISTS  "+DatabaseFields.TABLE_PROVIDERS+"  ( " +
                "provider_id            INTEGER, "+
                "provider_type_id       INTEGER, "+
                "provider_text          TEXT) ";

        String TABLE_REFER_FRIEND_QUERY = "CREATE TABLE IF NOT EXISTS  "+DatabaseFields.TABLE_REFER_FRIEND+"  ( " +
                "referal_id         INTEGER, "+
                "referee_name       TEXT, "+
                "referee_email      TEXT)";

        /**
         * DELETE FROM "sqlite_sequence";
         INSERT INTO "sqlite_sequence" VALUES('appointments',0);
         INSERT INTO "sqlite_sequence" VALUES('messages',0);
         INSERT INTO "sqlite_sequence" VALUES('vouchers',0);
         INSERT INTO "sqlite_sequence" VALUES('update_pages',0);

         */

        String TABLE_TOKENS_QUERY = "CREATE TABLE IF NOT EXISTS  "+DatabaseFields.TABLE_TOKENS+"  ( " +
                "person_id      INTEGER, "+
                "password       TEXT, "+
                "current_token  TEXT )";

        String TABLE_TREATMENT_TYPES_QUERY = "CREATE TABLE IF NOT EXISTS  "+DatabaseFields.TABLE_TREATMENT_TYPES+"  ( " +
	            "treatment_type_id	    INTEGER PRIMARY KEY AUTOINCREMENT, "+
	            "treatment_type_text	TEXT, "+
	            "treatment_type_code	TEXT, "+
	            "treatment_type_colour	TEXT )";

        String TABLE_UPDATE_PAGES_QUERY = "CREATE TABLE IF NOT EXISTS  "+DatabaseFields.TABLE_UPDATE_PAGES+"  ( " +
                "last_updated_messages 	TEXT,   "+
	            "last_updated_vouchers 	TEXT, "+
                "last_updated_appointments	TEXT, "+
	            "update_pages_id	INTEGER PRIMARY KEY AUTOINCREMENT )";

        String TABLE_VIDEOS_QUERY = "CREATE TABLE IF NOT EXISTS  "+DatabaseFields.TABLE_VIDEOS+"  ( " +
                "video_id   INTEGER, "+
                "video_text TEXT, "+
                "video_url  TEXT, "+
                "video_sort_order INTEGER)";

        String TABLE_VOUCHERS_QUERY = "CREATE TABLE IF NOT EXISTS  "+DatabaseFields.TABLE_VOUCHERS+"  ( " +
                "voucher_local_id	INTEGER PRIMARY KEY AUTOINCREMENT, "+
	            "voucher_id	INTEGER UNIQUE, "+
	            "voucher_type_id	INTEGER, "+
	            "voucher_treatment_type_id	INTEGER, "+
	            "voucher_fixed_amount	FLOAT, "+
	            "voucher_percent_amount	FLOAT, "+
	            "voucher_title	        TEXT, "+
	            "voucher_text	        TEXT, "+
	            "voucher_code	        TEXT, "+
	            "voucher_product_code	TEXT, "+
	            "voucher_valid_from_date	DATE, "+
	            "voucher_valid_until_date	DATE, "+
	            "voucher_is_read	            INTEGER, "+
	            "voucher_is_active	            INTEGER, "+
	            "voucher_is_redeemed	        INTEGER )";



        db.execSQL(TABLE_APPOINTMENTS_QUERY);
        db.execSQL(TABLE_MESSAGES_QUERY);
        db.execSQL(TABLE_PERSON_QUERY);
        db.execSQL(TABLE_PRACTICES_QUERY);
        db.execSQL(TABLE_PROVIDER_TYPES_QUERY);
        db.execSQL(TABLE_PROVIDERS_QUERY);
        db.execSQL(TABLE_REFER_FRIEND_QUERY);
        db.execSQL(TABLE_TOKENS_QUERY);
        db.execSQL(TABLE_TREATMENT_TYPES_QUERY);
        db.execSQL(TABLE_UPDATE_PAGES_QUERY);
        db.execSQL(TABLE_VIDEOS_QUERY);
        db.execSQL(TABLE_VOUCHERS_QUERY);

        Log.i("tag","tables created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public static synchronized DatabaseManager getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseManager(context);
        }
        return instance;
    }

    public synchronized SQLiteDatabase openDatabase() {
        if(mOpenCounter.incrementAndGet() == 1) {
            try{
                mDatabase = instance.getWritableDatabase();
            }catch(Exception e){
                Log.e(TAG,""+e);
            }
        }
        if (!mDatabase.isOpen())
        {
            try{
                mDatabase = instance.getWritableDatabase();
            }catch(Exception e){
                Log.e(TAG,""+e);
            }
        }
        return mDatabase;
    }

    public synchronized void closeDatabase() {
        if(mOpenCounter.decrementAndGet() == 0&&mDatabase.isOpen()) {
            try{
                mDatabase.close();
            }catch(Exception e){
                Log.e(TAG,""+e);
            }
        }
        //HLog.i(TAG,"After Closing Database "+mOpenCounter.get());
    }

    public synchronized void deleteDatabase(){
        //HLog.i(TAG,"Before Deleting Database "+mOpenCounter.get());
        try{
            context.deleteDatabase(DATABASE_NAME);
            mOpenCounter.set(0);
        }catch (Exception e) {

        }
        //HLog.i(TAG,"After Deleting Database "+mOpenCounter.get());

    }


}