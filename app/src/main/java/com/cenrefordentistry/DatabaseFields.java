package com.cenrefordentistry;

/**
 * Created by Ramu on 28-07-2017.
 */

public interface DatabaseFields {
    public String TABLE_APPOINTMENTS = "appointments";
    public String TABLE_MESSAGES = "messages";
    public String TABLE_PERSON = "person";
    public String TABLE_PRACTICES = "practices";
    public String TABLE_PROVIDER_TYPES = "provider_types";
    public String TABLE_PROVIDERS = "providers";
    public String TABLE_REFER_FRIEND = "refer_friend";
    public String TABLE_TOKENS = "token";
    public String TABLE_TREATMENT_TYPES = "treatment_types";
    public String TABLE_UPDATE_PAGES = "update_pages";
    public String TABLE_VIDEOS = "videos";
    public String TABLE_VOUCHERS = "vouchers";

    // appointment fields


    // mypractices

    public String COLUMN_SITE_ID = "site_id";
    public String COLUMN_SITE_TEXT = "site_text";
    public String COLUMN_SITE_LONGITUDE = "site_longitude";
    public String COLUMN_SITE_LATITUDE = "site_latitude";
    public String COLUMN_SITE_ADDRESS = "site_address";
    public String COLUMN_SITE_EMAIL_ADDRESS = "site_email_address";
    public String COLUMN_SITE_PHONE_NUMBER = "site_phone_number";
    public String COLUMN_SITE_POST_CODE = "site_post_code";
    public String COLUMN_SITE_IS_HOME = "site_is_home";

//treatment info
    public String COLUMN_TREATMENT_TYPE_ID   =   "treatment_type_id";
    public String COLUMN_TREATMENT_TYPE_TEXT   =   "treatment_type_text";
    public String COLUMN_TREATMENT_TYPE_CODE   =   "treatment_type_code";
    public String COLUMN_TREATMENT_TYPE_COLOR   =   "treatment_type_colour";
// personal details

    public String COLUMN_PERSON_ID              =   "person_id";
    public String COLUMN_PERSON_SITE_ID         =   "person_site_id";
    public String COLUMN_PERSON_PLAN_STATUS_ID  = "person_plan_status_id";
    public String COLUMN_PERSON_PLAN_RENEWAL_DATE = "person_plan_renewal_date";
    public String COLUMN_PERSON_CREATED_DATE    =   "person_created_date";
    public String COLUMN_PERSON_LAST_SEEN_DATE  =   "person_last_seen_date";
    public String COLUMN_PERSON_FIRST_NAME      =   "person_first_name";
    public String COLUMN_WELCOME_MESSAGE_1      =   "general_welcome_message_1";
    public String COLUMN_WELCOME_MESSAGE_2      =   "general_welcome_message_2";
    public String COLUMN_REFEREL_SUCCESS_COUNT  =   "referal_success_count";


// voucher details

    public String COLUMN_VOUCHER_ID                 =   "voucher_id";
    public String COLUMN_VOUCHER_TYPE_ID            =   "voucher_type_id";
    public String COLUMN_VOUCHER_TREATMENT_TYPE_ID  =   "voucher_treatment_type_id";
    public String COLUMN_VOUCHER_TEXT               =   "voucher_text";
    public String COLUMN_VOUCHER_VALID_FROM_DATE    =   "voucher_valid_from_date";
    public String COLUMN_VOUCHER_VALID_UNTIL_DATE   =   "voucher_valid_until_date";
    public String COLUMN_VOUCHER_FIXED_AMOUNT       =   "voucher_fixed_amount";
    public String COLUMN_VOUCHER_PERCENT_AMOUNT     =  "voucher_percent_amount";
    public String COLUMN_VOUCHER_CODE               =   "voucher_code";
    public String COLUMN_VOUCHER_PRODUCT_CODE       =   "voucher_product_code";
    public String COLUMN_VOUCHER_IS_REDEEMED        =   "voucher_is_redeemed";
    public String COLUMN_VOUCHER_TITLE              =   "voucher_title";
    public String COLUMN_VOUCHER_IS_READ            =   "voucher_is_read";
    public String COLUMN_VOUCHER_IS_ACTIVE          =   "voucher_is_active";


// Messages table

    public String COLUMN_MESSAGE_ID                 =   "message_id";
    public String COLUMN_MESSAGE_TYPE_ID            =   "message_type_id";
    public String COLUMN_MESSAGE_TEXT               =   "message_text";
    public String COLUMN_MESSAGE_VALID_FROM_DATE    =   "message_valid_from_date";
    public String COLUMN_MESSAGE_VALID_UNTIL_DATE   =   "message_valid_until_date";
    public String COLUMN_MESSAGE_IS_PROXIMITY       =   "message_is_proximity";
    public String COLUMN_MESSAGE_SUBJECT            =   "message_subject";
    public String COLUMN_MESSAGE_IS_ACTIVE          =   "message_is_active";
    public String COLUMN_MESSAGE_IS_READ            =   "message_is_read";


// Appointmetns table


    public String COLUMN_APPOINTMENT_ID                 =    "appointment_id";
    public String COLUMN_APPOINTMENT_PROVIDER_TEXT    =    "appointment_provider_text";
    public String COLUMN_APPOINTMENT_PROVIDER_ID        =   "appointment_provider_id";
    public String COLUMN_APPOINTMENT_SITE_ID        =   "appointment_site_id";
    public String COLUMN_APPOINTMENT_PERSON_ID        =   "appointment_person_id";
    public String COLUMN_APPOINTMENT_TYPE_ID        =   "appointment_type_id";
    public String COLUMN_APPOINTMENT_REMOTE_ID        =   "appointment_remote_id";
    public String COLUMN_APPOINTMENT_STATUS_ID        =   "appointment_status_id";
    public String COLUMN_APPOINTMENT_DATETIME        =   "appointment_datetime";
    public String COLUMN_APPOINTMENT_BOOKING_PRICE        =   "appointment_booking_price";




}
