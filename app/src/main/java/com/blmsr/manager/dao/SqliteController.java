package com.blmsr.manager.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.blmsr.manager.db.CategoryEntryTableContract;
import com.blmsr.manager.db.CategoryTableContract;
import com.blmsr.manager.db.DatabaseConstants;
import com.blmsr.manager.db.UserTableContract;

/**
 * Created by LakshmiMadhav on 7/30/2015.
 */
public class SqliteController extends SQLiteOpenHelper implements DatabaseConstants {
    private static final String LOGCAT = null;

    public SqliteController(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(LOGCAT, "Created");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CategoryTableContract.SQL_CREATE_CATEGORY_TABLE);
        db.execSQL(CategoryEntryTableContract.SQL_CREATE_CATEGORY_ENTRY_TABLE);
        db.execSQL(UserTableContract.SQL_CREATE_USER_TABLE);

        Log.d(LOGCAT, "starting inserting default templates");
        db.execSQL(CategoryTableContract.SQL_INSERT_CARDS_CATEGORY);
        db.execSQL(CategoryTableContract.SQL_INSERT_COMPUTERS_LOGIN_CATEGORY);
        db.execSQL(CategoryTableContract.SQL_INSERT_E_BANKING_CATEGORY);
        db.execSQL(CategoryTableContract.SQL_INSERT_EMAIL_CATEGORY);
        db.execSQL(CategoryTableContract.SQL_INSERT_MOBILE_APPS_CATEGORY);
        db.execSQL(CategoryTableContract.SQL_INSERT_WEB_ACCOUNTS_CATEGORY);
        Log.d(LOGCAT, "successfully added default templates");
        Log.d(LOGCAT, "TableS created successfully");
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(CategoryTableContract.SQL_DELETE_CATEGORY_TABLE_QUERY);
        db.execSQL(CategoryEntryTableContract.SQL_DELETE_CATEGORY_ENTRY_TABLE_QUERY);
        db.execSQL(UserTableContract.SQL_DELETE_USER_TABLE_QUERY);

        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
}
