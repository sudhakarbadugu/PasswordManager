package com.blmsr.manager.db;

import android.provider.BaseColumns;

/**
 * Created by LakshmiMadhav on 8/4/2015.
 */
public interface CategoryTableContract extends DatabaseConstants {

    public static final String TABLE_NAME = "Categories";
    public static final String COLUMN_NAME_CATEGORY_NAME = "CATEGORYNAME";
    public static final String COLUMN_NAME_CATEGORY_ICON_NAME = "ICONNAME";
    public static final String COLUMN_NAME_FIELD_1 = "FIELD_1";
    public static final String COLUMN_NAME_FIELD_2 = "FIELD_2";
    public static final String COLUMN_NAME_FIELD_3 = "FIELD_3";
    public static final String COLUMN_NAME_FIELD_4 = "FIELD_4";
    public static final String COLUMN_NAME_FIELD_5 = "FIELD_5";
    public static final String COLUMN_NAME_FIELD_6 = "FIELD_6";
    public static final String COLUMN_NAME_FIELD_7 = "FIELD_7";
    public static final String COLUMN_NAME_FIELD_8 = "FIELD_8";
    public static final String COLUMN_NAME_FIELD_9 = "FIELD_9";
    public static final String COLUMN_NAME_FIELD_10 = "FIELD_10";
    public static final String COLUMN_NAME_FIELD_11 = "FIELD_11";
    public static final String COLUMN_NAME_FIELD_12 = "FIELD_12";
    public static final String COLUMN_NAME_FIELD_13 = "FIELD_13";
    public static final String COLUMN_NAME_FIELD_14 = "FIELD_14";
    public static final String COLUMN_NAME_FIELD_15 = "FIELD_15";


    String SQL_CREATE_CATEGORY_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    _ID + " INTEGER PRIMARY KEY," +
                    COLUMN_NAME_CATEGORY_NAME + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NAME_CATEGORY_ICON_NAME + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NAME_FIELD_1 + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NAME_FIELD_2 + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NAME_FIELD_3 + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NAME_FIELD_4 + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NAME_FIELD_5 + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NAME_FIELD_6 + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NAME_FIELD_7 + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NAME_FIELD_8 + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NAME_FIELD_9 + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NAME_FIELD_10 + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NAME_FIELD_11 + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NAME_FIELD_12 + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NAME_FIELD_13 + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NAME_FIELD_14 + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NAME_FIELD_15 + TEXT_TYPE +
                    ")";


    String SQL_DELETE_CATEGORY_TABLE_QUERY = "DROP TABLE IF EXISTS " + TABLE_NAME;
    String SQL_CATEGORY_PROJECTION[] = {_ID, COLUMN_NAME_CATEGORY_NAME, COLUMN_NAME_CATEGORY_ICON_NAME,
            COLUMN_NAME_FIELD_1,
            COLUMN_NAME_FIELD_2,
            COLUMN_NAME_FIELD_3,
            COLUMN_NAME_FIELD_4,
            COLUMN_NAME_FIELD_5,
            COLUMN_NAME_FIELD_6,
            COLUMN_NAME_FIELD_7,
            COLUMN_NAME_FIELD_8,
            COLUMN_NAME_FIELD_9,
            COLUMN_NAME_FIELD_10,
            COLUMN_NAME_FIELD_11,
            COLUMN_NAME_FIELD_12,
            COLUMN_NAME_FIELD_13,
            COLUMN_NAME_FIELD_14,
            COLUMN_NAME_FIELD_15
    };

    String SQL_INSERT_CARDS_CATEGORY = new StringBuilder("INSERT INTO ").append(TABLE_NAME)
            .append("(")
            .append(COLUMN_NAME_CATEGORY_NAME).append(COMMA_SEP)
            .append(COLUMN_NAME_CATEGORY_ICON_NAME).append(COMMA_SEP)
            .append(COLUMN_NAME_FIELD_1).append(COMMA_SEP)
            .append(COLUMN_NAME_FIELD_2).append(COMMA_SEP)
            .append(COLUMN_NAME_FIELD_3).append(COMMA_SEP)
            .append(COLUMN_NAME_FIELD_4).append(COMMA_SEP)
            .append(COLUMN_NAME_FIELD_5).append(COMMA_SEP)
            .append(COLUMN_NAME_FIELD_6).append(COMMA_SEP)
            .append(COLUMN_NAME_FIELD_7).append(") VALUES (")
            .append("'Cards'").append(COMMA_SEP)
            .append("'card_icon'").append(COMMA_SEP)
            .append("'Card name'").append(COMMA_SEP)
            .append("'Card Number'").append(COMMA_SEP)
            .append("'Pin'").append(COMMA_SEP)
            .append("'Holder'").append(COMMA_SEP)
            .append("'Expiry'").append(COMMA_SEP)
            .append("'Security code'").append(COMMA_SEP)
            .append("'CVV'").append(")").toString();

    String SQL_INSERT_COMPUTERS_LOGIN_CATEGORY = new StringBuilder("INSERT INTO ").append(TABLE_NAME)
            .append("(")
            .append(COLUMN_NAME_CATEGORY_NAME).append(COMMA_SEP)
            .append(COLUMN_NAME_CATEGORY_ICON_NAME).append(COMMA_SEP)
            .append(COLUMN_NAME_FIELD_1).append(COMMA_SEP)
            .append(COLUMN_NAME_FIELD_2).append(COMMA_SEP)
            .append(COLUMN_NAME_FIELD_3).append(COMMA_SEP)
            .append(COLUMN_NAME_FIELD_4).append(") VALUES (")
            .append("'Computer Logins'").append(COMMA_SEP)
            .append("'computer_icon'").append(COMMA_SEP)
            .append("'Account'").append(COMMA_SEP)
            .append("'Name'").append(COMMA_SEP)
            .append("'Password'").append(COMMA_SEP)
            .append("'Note'").append(")").toString();

    String SQL_INSERT_E_BANKING_CATEGORY = new StringBuilder("INSERT INTO ").append(TABLE_NAME)
            .append("(")
            .append(COLUMN_NAME_CATEGORY_NAME).append(COMMA_SEP)
            .append(COLUMN_NAME_CATEGORY_ICON_NAME).append(COMMA_SEP)
            .append(COLUMN_NAME_FIELD_1).append(COMMA_SEP)
            .append(COLUMN_NAME_FIELD_2).append(COMMA_SEP)
            .append(COLUMN_NAME_FIELD_3).append(COMMA_SEP)
            .append(COLUMN_NAME_FIELD_4).append(COMMA_SEP)
            .append(COLUMN_NAME_FIELD_5).append(") VALUES (")
            .append("'e-Banking'").append(COMMA_SEP)
            .append("'e_bank_icon'").append(COMMA_SEP)
            .append("'Bank'").append(COMMA_SEP)
            .append("'Website'").append(COMMA_SEP)
            .append("'Name'").append(COMMA_SEP)
            .append("'Password'").append(COMMA_SEP)
            .append("'Note'").append(")").toString();


    String SQL_INSERT_EMAIL_CATEGORY = new StringBuilder("INSERT INTO ").append(TABLE_NAME)
            .append("(")
            .append(COLUMN_NAME_CATEGORY_NAME).append(COMMA_SEP)
            .append(COLUMN_NAME_CATEGORY_ICON_NAME).append(COMMA_SEP)
            .append(COLUMN_NAME_FIELD_1).append(COMMA_SEP)
            .append(COLUMN_NAME_FIELD_2).append(COMMA_SEP)
            .append(COLUMN_NAME_FIELD_3).append(COMMA_SEP)
            .append(COLUMN_NAME_FIELD_4).append(COMMA_SEP)
            .append(COLUMN_NAME_FIELD_5).append(") VALUES (")
            .append("'Email Accounts'").append(COMMA_SEP)
            .append("'email_icon'").append(COMMA_SEP)
            .append("'Account'").append(COMMA_SEP)
            .append("'Email'").append(COMMA_SEP)
            .append("'Password'").append(COMMA_SEP)
            .append("'Web site'").append(COMMA_SEP)
            .append("'Note'").append(")").toString();

    String SQL_INSERT_MOBILE_APPS_CATEGORY = new StringBuilder("INSERT INTO ").append(TABLE_NAME)
            .append("(")
            .append(COLUMN_NAME_CATEGORY_NAME).append(COMMA_SEP)
            .append(COLUMN_NAME_CATEGORY_ICON_NAME).append(COMMA_SEP)
            .append(COLUMN_NAME_FIELD_1).append(COMMA_SEP)
            .append(COLUMN_NAME_FIELD_2).append(COMMA_SEP)
            .append(COLUMN_NAME_FIELD_3).append(COMMA_SEP)
            .append(COLUMN_NAME_FIELD_4).append(COMMA_SEP)
            .append(COLUMN_NAME_FIELD_5).append(COMMA_SEP)
            .append(COLUMN_NAME_FIELD_6).append(") VALUES (")
            .append("'Mobile apps'").append(COMMA_SEP)
            .append("'mobile_apps_icon'").append(COMMA_SEP)
            .append("'App name'").append(COMMA_SEP)
            .append("'User name'").append(COMMA_SEP)
            .append("'Password'").append(COMMA_SEP)
            .append("'Signed username'").append(COMMA_SEP)
            .append("'Mobile number'").append(COMMA_SEP)
            .append("'Notes'").append(")").toString();

    String SQL_INSERT_WEB_ACCOUNTS_CATEGORY = new StringBuilder("INSERT INTO ").append(TABLE_NAME)
            .append("(")
            .append(COLUMN_NAME_CATEGORY_NAME).append(COMMA_SEP)
            .append(COLUMN_NAME_CATEGORY_ICON_NAME).append(COMMA_SEP)
            .append(COLUMN_NAME_FIELD_1).append(COMMA_SEP)
            .append(COLUMN_NAME_FIELD_2).append(COMMA_SEP)
            .append(COLUMN_NAME_FIELD_3).append(COMMA_SEP)
            .append(COLUMN_NAME_FIELD_4).append(") VALUES (")
            .append("'Web Accounts'").append(COMMA_SEP)
            .append("'web_icon'").append(COMMA_SEP)
            .append("'Web site'").append(COMMA_SEP)
            .append("'User name'").append(COMMA_SEP)
            .append("'Password'").append(COMMA_SEP)
            .append("'Mobile number'").append(")").toString();

}
