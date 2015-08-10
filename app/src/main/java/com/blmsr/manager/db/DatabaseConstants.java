package com.blmsr.manager.db;

import android.provider.BaseColumns;

/**
 * Created by LakshmiMadhav on 8/5/2015.
 */
public interface DatabaseConstants extends BaseColumns{
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "PasswordManager.db";

    static final String TEXT_TYPE = " TEXT";
    static final String COMMA_SEP = ",";

}
