package com.blmsr.manager.db;

/**
 * Created by LakshmiMadhav on 8/5/2015.
 */
public interface UserTableContract extends DatabaseConstants {
    public static final String TABLE_NAME = "User";
    public static final String COLUMN_NAME_PASSWORD = "password";

    String SQL_CREATE_USER_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    _ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
                    COLUMN_NAME_PASSWORD + TEXT_TYPE + ");";

    String SQL_DELETE_USER_TABLE_QUERY = "DROP TABLE IF EXISTS " + TABLE_NAME;
    String SQL_USER_PROJECTION[] = {_ID, COLUMN_NAME_PASSWORD};
}
