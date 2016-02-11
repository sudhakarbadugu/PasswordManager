package com.blmsr.manager.db;

/**
 * Created by LakshmiMadhav on 8/5/2015.
 */
public interface CategoryEntryTableContract extends DatabaseConstants {
    public static final String TABLE_NAME = "CategoryEntry";
    public static final String COLUMN_NAME_CATEGORY_NAME = "CATEGORY_NAME";
    public static final String COLUMN_NAME_CATEGORY_ID = "CATEGORY_ID";
    public static final String COLUMN_NAME_CATEGORY_ENTRY_ID = "CATEGORY_ENTY_ID";
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


    String SQL_CREATE_CATEGORY_ENTRY_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_NAME_CATEGORY_ENTRY_ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
                    COLUMN_NAME_CATEGORY_ID + " INTEGER" + COMMA_SEP +
                    COLUMN_NAME_CATEGORY_NAME + TEXT_TYPE + COMMA_SEP +
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
                    COLUMN_NAME_FIELD_15 + TEXT_TYPE + COMMA_SEP +
                    "FOREIGN KEY(" + COLUMN_NAME_CATEGORY_ENTRY_ID + ") REFERENCES " + CategoryTableContract.TABLE_NAME + "(" + CategoryTableContract._ID + "));";


    String SQL_DELETE_CATEGORY_ENTRY_TABLE_QUERY = "DROP TABLE IF EXISTS " + TABLE_NAME;

    /**
     * Define a projection that specifies which columns from the database
     * you will actually use after this query.
     */
    String SQL_CATEGORY_ENTRY_PROJECTION[] = {COLUMN_NAME_CATEGORY_ENTRY_ID, COLUMN_NAME_CATEGORY_ID, COLUMN_NAME_CATEGORY_NAME,
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


    /**
     * Define a projection that specifies which columns from the database
     * you will actually use after this query.
     */
    String CATEGORY_ENTRY_EXPORT_COLUMNS[] = {COLUMN_NAME_CATEGORY_NAME,
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

    /**
     * query to count the no of entries based on the entry id from parent table.
     */
    String SQL_CATEGORY_ENTRIES_COUNT_ID = "SELECT COUNT(*) FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME_CATEGORY_ID + " = ?";
    /**
     * Which row to update, based on the ID
      */
    String SQL_CATEGORY_ENTRY_SELECTION = COLUMN_NAME_CATEGORY_ID + "= ?" ;
}
