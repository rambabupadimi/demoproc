package com.cenrefordentistry;

/**
 * Created by Ramu on 18-07-2017.
 */

public interface AppConstants {


    public final String DATABASE_NAME ="cfd_db";
    public final int DATABASE_VERSION = 1;


    String APP_URL = "https://stager.centrefordentistry.com/ephemeris/";
    public  final String GET_TOKEN_API  =   APP_URL+"despatcher/token_generator";
    public  final String GET_PERSON     =   APP_URL+"api/person";
    public  final String GET_PRACTICES  =   APP_URL+"api/practices";
    public  final String GET_TREATMENT_TYPES = APP_URL+"api/treatment_types";
    public final String GET_VOUCHERS    =   APP_URL+"api/vouchers";
    public final String GET_MESSAGES    =   APP_URL+"api/messages";
    public final String GET_EXISTING_APPOINTMENTS = APP_URL+"api/appointments_existing";

    public final String GET_REQUEST_CALL_BACK = "api/request_contact";

    public final String USERNAME = "username";
    public final String PASSWORD = "password";
    public final String GRANT_TYPE = "grant_type";
    public final String COLOR_CODE = "color_code";



    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String IMAGE = "image";

    public static final String MY_PLAN_MESSAGE1= "    Our Annual Dental plan offers you a cost effective way to help look after you teeth  and prevent\n" +
            "        problems from building up. The Dental plan is designed to help you plan for your oral health\n" +
            "        over time so we can look after you and your smile.\n";

    public static final String MY_PLAN_MESSAGE2 = "  A year\\'s membership to the CFD annual dental plan entities you to:\n" +
            "        <ul><br/><br>\n" +
            "            <li>1. Routine Dental Check-Ups</li><br/>\n" +
            "            <li>2. Routine scale and Polish Appointments</li><br/>\n" +
            "            <li>3. Considerable Cost Savings</li><br/>\n" +
            "            <li>4. 10% off most dental treatments</li><br/>\n" +
            "            <li>5. Discounts on dental care products for our plan members</li><br/>\n" +
            "            <li>6. Free Check-Up and Flouride Application for children </li><br/>\n" +
            "        </ul>\n" +
            "    ";
    public static final String TAG_HOME = "Home";
    public static final String TAG_APPOINTEMNTS = "Appointments";
    public static final String TAG_MESSAGES = "Messages";
    public static final String TAG_MYPLANS = "MyPlans";
    public static final String TAG_MYPRACTICE = "MyPractice";
    public static final String TAG_REFERFRIEND = "ReferFriend";
    public static final String TAG_TREATMENTINFO = "TreatmentInfo";
    public static final String TAG_VOUCHERWALLET = "VoucherWallet";


    public static final int HOME_INDEX = 0;
    public static final int MYPRACTICE_INDEX = 1;
    public static final int APPOINTMENTS_INDEX = 2;
    public static final int MESSAGES_INDEX = 3;
    public static final int VOUCHERWALLET_INDEX = 4;
    public static final int TREATMENTINFO_INDEX = 5;
    public static final int MYPLANS_INDEX = 6;
    public static final int REFERFRIEND_INDEX = 7;


}
