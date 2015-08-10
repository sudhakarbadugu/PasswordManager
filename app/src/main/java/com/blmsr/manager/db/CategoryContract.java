package com.blmsr.manager.db;

import android.provider.BaseColumns;

/**
 * Created by LakshmiMadhav on 7/31/2015.
 */
public final class CategoryContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public CategoryContract() {}

    /* Inner class that defines the table contents */
    public static abstract class CategoryEntry implements BaseColumns {
        public static final String TABLE_NAME = "EmailAccounts";

        public static final String COLUMN_NAME_ACCOUNT = "Account";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_PASSWORD = "password";
    }
}
