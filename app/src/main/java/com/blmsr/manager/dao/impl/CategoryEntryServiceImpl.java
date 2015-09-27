package com.blmsr.manager.dao.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.blmsr.manager.dao.CategoryEntryService;
import com.blmsr.manager.dao.SqliteController;
import com.blmsr.manager.db.CategoryEntryTableContract;
import com.blmsr.manager.models.CategoryEntry;
import com.blmsr.manager.util.EncryptionUtil;
import com.blmsr.manager.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static com.blmsr.manager.util.EncryptionUtil.*;

/**
 * Created by LakshmiMadhav on 8/5/2015.
 */
public class CategoryEntryServiceImpl implements CategoryEntryService,CategoryEntryTableContract {
    private SqliteController sqliteController;

    public CategoryEntryServiceImpl(SqliteController sqliteController) {
        this.sqliteController = sqliteController;
    }

    @Override
    public long save(CategoryEntry theCategoryEntry) {
        SQLiteDatabase database = sqliteController.getWritableDatabase();
        ContentValues values= getContentValues(theCategoryEntry);

        long aRows = database.insert(TABLE_NAME, null, values);
        database.close();

        return aRows;
    }

    @Override
    public long update(CategoryEntry theEntity) {
        SQLiteDatabase database = sqliteController.getWritableDatabase();
        ContentValues values = getContentValues(theEntity);

        // Which row to update, based on the ID
        String selection = COLUMN_NAME_CATEGORY_ENTRY_ID + "= ? and " + COLUMN_NAME_CATEGORY_ID + "= ?" ;
        String[] selectionArgs = { String.valueOf(theEntity.getCategoryEntryId()), String.valueOf(theEntity.getCategoryId()) };

        int count = database.update(TABLE_NAME, values, selection, selectionArgs);
        database.close();


        return count;
    }

    @Override
    public long delete(CategoryEntry theEntity) {
        SQLiteDatabase database = sqliteController.getWritableDatabase();

        // Which row to update, based on the ID
        String selection = COLUMN_NAME_CATEGORY_ENTRY_ID + "= ? and " + COLUMN_NAME_CATEGORY_ID + "= ?" ;

        String[] selectionArgs = { String.valueOf(theEntity.getCategoryEntryId()), String.valueOf(theEntity.getCategoryId()) };

        // Issue SQL statement.
        int count = database.delete(TABLE_NAME, selection, selectionArgs);
        database.close();

        return count;
    }

    @Override
    public List<CategoryEntry> getCategoryByID(int theCategoryID) {
        ArrayList<CategoryEntry> categoryEntriesList = new ArrayList<CategoryEntry>();
        // How you want the results sorted in the resulting Cursor
        String sortOrder = COLUMN_NAME_FIELD_1 + " ASC";

        SQLiteDatabase database = sqliteController.getReadableDatabase();
        String[] selectionArgs = { String.valueOf(theCategoryID) };

        Cursor cursor = database.query(
                TABLE_NAME,  // The table to query
                SQL_CATEGORY_ENTRY_PROJECTION,                               // The columns to return
                SQL_CATEGORY_ENTRY_SELECTION,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        if (cursor.moveToFirst()) {
            do {
                CategoryEntry aCategoryEntry = getCategoryEntryModel(cursor);
                categoryEntriesList.add(aCategoryEntry);
            } while (cursor.moveToNext());
        }
        database.close();

        return categoryEntriesList;
    }
    @Override
    public List<CategoryEntry> getCategories() {
        ArrayList<CategoryEntry> categoryEntriesList = new ArrayList<CategoryEntry>();

        // How you want the results sorted in the resulting Cursor
        String sortOrder = COLUMN_NAME_FIELD_1 + " ASC";

        SQLiteDatabase database = sqliteController.getReadableDatabase();

        Cursor cursor = database.query(
                TABLE_NAME,  // The table to query
                SQL_CATEGORY_ENTRY_PROJECTION,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        if (cursor.moveToFirst()) {
            do {
                CategoryEntry aCategoryEntry = getCategoryEntryModel(cursor);
                categoryEntriesList.add(aCategoryEntry);
            } while (cursor.moveToNext());
        }
        database.close();

        // return contact list
        return categoryEntriesList;
    }

    /**
     * Returns the ContentValues instance. It is used to store the values to db.
     *
     * @param theCategoryEntry
     * @return the ContentValues instance.
     */
    private ContentValues getContentValues(CategoryEntry theCategoryEntry)
    {
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME_CATEGORY_ID,theCategoryEntry.getCategoryId());
        values.put(COLUMN_NAME_CATEGORY_NAME, getEncryptValue(theCategoryEntry.getCategoryName()));
        values.put(COLUMN_NAME_FIELD_1, getEncryptValue(theCategoryEntry.getField1()));
        values.put(COLUMN_NAME_FIELD_2, getEncryptValue(theCategoryEntry.getField2()));
        values.put(COLUMN_NAME_FIELD_3, getEncryptValue(theCategoryEntry.getField3()));
        values.put(COLUMN_NAME_FIELD_4, getEncryptValue(theCategoryEntry.getField4()));
        values.put(COLUMN_NAME_FIELD_5, getEncryptValue(theCategoryEntry.getField5()));
        values.put(COLUMN_NAME_FIELD_6, getEncryptValue(theCategoryEntry.getField6()));
        values.put(COLUMN_NAME_FIELD_7, getEncryptValue(theCategoryEntry.getField7()));
        values.put(COLUMN_NAME_FIELD_8, getEncryptValue(theCategoryEntry.getField8()));
        values.put(COLUMN_NAME_FIELD_9, getEncryptValue(theCategoryEntry.getField9()));
        values.put(COLUMN_NAME_FIELD_10, getEncryptValue(theCategoryEntry.getField10()));
        values.put(COLUMN_NAME_FIELD_11, getEncryptValue(theCategoryEntry.getField11()));
        values.put(COLUMN_NAME_FIELD_12, getEncryptValue(theCategoryEntry.getField12()));
        values.put(COLUMN_NAME_FIELD_13, getEncryptValue(theCategoryEntry.getField13()));
        values.put(COLUMN_NAME_FIELD_14, getEncryptValue(theCategoryEntry.getField14()));
        values.put(COLUMN_NAME_FIELD_15, getEncryptValue(theCategoryEntry.getField15()));

        return values;
    }

    /**
     * Returns the CategoryEntry instance. CategoryEntry instance constructed from the db values.
     * @param theCursor
     * @return the CategoryEntry instance.
     */
    private CategoryEntry getCategoryEntryModel(Cursor theCursor)
    {
        CategoryEntry aCategoryEntry = new CategoryEntry();
        aCategoryEntry.setCategoryEntryId(theCursor.getInt(theCursor.getColumnIndexOrThrow(COLUMN_NAME_CATEGORY_ENTRY_ID)));
        aCategoryEntry.setCategoryId(theCursor.getInt(theCursor.getColumnIndexOrThrow(COLUMN_NAME_CATEGORY_ID)));
        aCategoryEntry.setCategoryName(getDecryptValue(theCursor.getString(theCursor.getColumnIndexOrThrow(COLUMN_NAME_CATEGORY_NAME))));
        aCategoryEntry.setField1(getDecryptValue(theCursor.getString(theCursor.getColumnIndexOrThrow(COLUMN_NAME_FIELD_1))));
        aCategoryEntry.setField2(getDecryptValue(theCursor.getString(theCursor.getColumnIndexOrThrow(COLUMN_NAME_FIELD_2))));
        aCategoryEntry.setField3(getDecryptValue(theCursor.getString(theCursor.getColumnIndexOrThrow(COLUMN_NAME_FIELD_3))));
        aCategoryEntry.setField4(getDecryptValue(theCursor.getString(theCursor.getColumnIndexOrThrow(COLUMN_NAME_FIELD_4))));
        aCategoryEntry.setField5(getDecryptValue(theCursor.getString(theCursor.getColumnIndexOrThrow(COLUMN_NAME_FIELD_5))));
        aCategoryEntry.setField6(getDecryptValue(theCursor.getString(theCursor.getColumnIndexOrThrow(COLUMN_NAME_FIELD_6))));
        aCategoryEntry.setField7(getDecryptValue(theCursor.getString(theCursor.getColumnIndexOrThrow(COLUMN_NAME_FIELD_7))));
        aCategoryEntry.setField8(getDecryptValue(theCursor.getString(theCursor.getColumnIndexOrThrow(COLUMN_NAME_FIELD_8))));
        aCategoryEntry.setField9(getDecryptValue(theCursor.getString(theCursor.getColumnIndexOrThrow(COLUMN_NAME_FIELD_9))));
        aCategoryEntry.setField10(getDecryptValue(theCursor.getString(theCursor.getColumnIndexOrThrow(COLUMN_NAME_FIELD_10))));
        aCategoryEntry.setField11(getDecryptValue(theCursor.getString(theCursor.getColumnIndexOrThrow(COLUMN_NAME_FIELD_11))));
        aCategoryEntry.setField12(getDecryptValue(theCursor.getString(theCursor.getColumnIndexOrThrow(COLUMN_NAME_FIELD_12))));
        aCategoryEntry.setField13(getDecryptValue(theCursor.getString(theCursor.getColumnIndexOrThrow(COLUMN_NAME_FIELD_13))));
        aCategoryEntry.setField14(getDecryptValue(theCursor.getString(theCursor.getColumnIndexOrThrow(COLUMN_NAME_FIELD_14))));
        aCategoryEntry.setField15(getDecryptValue(theCursor.getString(theCursor.getColumnIndexOrThrow(COLUMN_NAME_FIELD_15))));

        return aCategoryEntry;
    }

    /**
     * returns the encrypted value.
     *
     * @param theString
     * @return
     */
    private String getEncryptValue(String theString) {
        if (StringUtils.isNullOrEmpty(theString)) {
            return theString;
        }

        return EncryptionUtil.encrypt(theString);
    }

    /**
     * Gets the decrypted value.
     *
     * @param theString
     * @return
     */
    private String getDecryptValue(String theString) {
        if (StringUtils.isNullOrEmpty(theString)) {
            return theString;
        }

        return EncryptionUtil.decrypt(theString);
    }
}
