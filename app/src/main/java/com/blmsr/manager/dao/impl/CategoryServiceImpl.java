package com.blmsr.manager.dao.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.blmsr.manager.dao.CategoryService;
import com.blmsr.manager.dao.SqliteController;
import com.blmsr.manager.db.CategoryTableContract;
import com.blmsr.manager.models.Category;
import com.blmsr.manager.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LakshmiMadhav on 8/4/2015.
 */
public class CategoryServiceImpl implements CategoryService, CategoryTableContract {
    private SqliteController sqliteController;

    public CategoryServiceImpl(SqliteController sqliteController) {
        this.sqliteController = sqliteController;
    }

    @Override
    public long save(Category theEntity) {

        SQLiteDatabase database = sqliteController.getWritableDatabase();
        ContentValues values = getContentValues(theEntity);

        long aRows = database.insert(TABLE_NAME, null, values);
        database.close();

        return aRows;
    }

    @Override
    public long update(Category theEntity) {
        SQLiteDatabase database = sqliteController.getWritableDatabase();
        ContentValues values = getContentValues(theEntity);

        // Which row to update, based on the ID
        String selection = _ID + "= ?";
        String[] selectionArgs = {String.valueOf(theEntity.getCategoryId())};

        int count = database.update(TABLE_NAME, values, selection, selectionArgs);
        database.close();

        return count;

    }

    @Override
    public long delete(Category theEntity) {
        SQLiteDatabase database = sqliteController.getWritableDatabase();

        // Which row to update, based on the ID
        String selection = _ID + "= ? and " + COLUMN_NAME_CATEGORY_NAME + "= ?";
        String[] selectionArgs = {String.valueOf(theEntity.getCategoryId()), theEntity.getCategoryName()};
        // Issue SQL statement.
        int count = database.delete(TABLE_NAME, selection, selectionArgs);
        database.close();

        return count;
    }

    @Override
    public Category getCategiryByID(int theId) {
        // How you want the results sorted in the resulting Cursor
        String sortOrder = COLUMN_NAME_FIELD_1 + " ASC";

        SQLiteDatabase database = sqliteController.getReadableDatabase();
        String[] selectionArgs = {String.valueOf(theId)};
        // Which row to update, based on the ID
        String selection = _ID + "= ?";
        Cursor cursor = database.query(
                TABLE_NAME,  // The table to query
                SQL_CATEGORY_PROJECTION,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        Category aCategory = null;
        if (cursor.moveToFirst()) {
            aCategory = getCategoryEntryModel(cursor);
        }
        database.close();
        return aCategory;
    }

    @Override
    public List<Category> getCategiries() {

        ArrayList<Category> categoryList = new ArrayList<Category>();

        // How you want the results sorted in the resulting Cursor
        String sortOrder = COLUMN_NAME_CATEGORY_NAME + " ASC";

        SQLiteDatabase database = sqliteController.getReadableDatabase();

        Cursor cursor = database.query(
                TABLE_NAME,  // The table to query
                SQL_CATEGORY_PROJECTION,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        if (cursor.moveToFirst()) {
            do {
                Category aCategory = getCategoryEntryModel(cursor);
                categoryList.add(aCategory);
            } while (cursor.moveToNext());
        }

        database.close();
        // return contact list
        return categoryList;
    }


    private ContentValues getContentValues(Category theCategory) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_CATEGORY_NAME, theCategory.getCategoryName());
        values.put(COLUMN_NAME_CATEGORY_ICON_NAME, theCategory.getCategoryIconName());
        values.put(COLUMN_NAME_FIELD_1, theCategory.getField1());
        values.put(COLUMN_NAME_FIELD_2, theCategory.getField2());
        values.put(COLUMN_NAME_FIELD_3, theCategory.getField3());
        values.put(COLUMN_NAME_FIELD_4, theCategory.getField4());
        values.put(COLUMN_NAME_FIELD_5, theCategory.getField5());
        values.put(COLUMN_NAME_FIELD_6, theCategory.getField6());
        values.put(COLUMN_NAME_FIELD_7, theCategory.getField7());
        values.put(COLUMN_NAME_FIELD_8, theCategory.getField8());
        values.put(COLUMN_NAME_FIELD_9, theCategory.getField9());
        values.put(COLUMN_NAME_FIELD_10, theCategory.getField10());
        values.put(COLUMN_NAME_FIELD_11, theCategory.getField11());
        values.put(COLUMN_NAME_FIELD_12, theCategory.getField12());
        values.put(COLUMN_NAME_FIELD_13, theCategory.getField13());
        values.put(COLUMN_NAME_FIELD_14, theCategory.getField14());
        values.put(COLUMN_NAME_FIELD_15, theCategory.getField15());
        values.put(COLUMN_NAME_FIELD_TYPES, getFieldTypesFromBoolean(theCategory));

        return values;
    }

    private Category getCategoryEntryModel(Cursor theCursor) {
        Category aCategory = new Category();
        aCategory.setCategoryId(theCursor.getInt(theCursor.getColumnIndexOrThrow(_ID)));
        aCategory.setCategoryName(theCursor.getString(theCursor.getColumnIndexOrThrow(COLUMN_NAME_CATEGORY_NAME)));
        aCategory.setCategoryIconName(theCursor.getString(theCursor.getColumnIndexOrThrow(COLUMN_NAME_CATEGORY_ICON_NAME)));
        aCategory.setField1(theCursor.getString(theCursor.getColumnIndexOrThrow(COLUMN_NAME_FIELD_1)));
        aCategory.setField2(theCursor.getString(theCursor.getColumnIndexOrThrow(COLUMN_NAME_FIELD_2)));
        aCategory.setField3(theCursor.getString(theCursor.getColumnIndexOrThrow(COLUMN_NAME_FIELD_3)));
        aCategory.setField4(theCursor.getString(theCursor.getColumnIndexOrThrow(COLUMN_NAME_FIELD_4)));
        aCategory.setField5(theCursor.getString(theCursor.getColumnIndexOrThrow(COLUMN_NAME_FIELD_5)));
        aCategory.setField6(theCursor.getString(theCursor.getColumnIndexOrThrow(COLUMN_NAME_FIELD_6)));
        aCategory.setField7(theCursor.getString(theCursor.getColumnIndexOrThrow(COLUMN_NAME_FIELD_7)));
        aCategory.setField8(theCursor.getString(theCursor.getColumnIndexOrThrow(COLUMN_NAME_FIELD_8)));
        aCategory.setField9(theCursor.getString(theCursor.getColumnIndexOrThrow(COLUMN_NAME_FIELD_9)));
        aCategory.setField10(theCursor.getString(theCursor.getColumnIndexOrThrow(COLUMN_NAME_FIELD_10)));
        aCategory.setField11(theCursor.getString(theCursor.getColumnIndexOrThrow(COLUMN_NAME_FIELD_11)));
        aCategory.setField12(theCursor.getString(theCursor.getColumnIndexOrThrow(COLUMN_NAME_FIELD_12)));
        aCategory.setField13(theCursor.getString(theCursor.getColumnIndexOrThrow(COLUMN_NAME_FIELD_13)));
        aCategory.setField14(theCursor.getString(theCursor.getColumnIndexOrThrow(COLUMN_NAME_FIELD_14)));
        aCategory.setField15(theCursor.getString(theCursor.getColumnIndexOrThrow(COLUMN_NAME_FIELD_15)));
        aCategory.setDefaultFieldVisibilities(getFieldTypesToBoolean(theCursor.getString(theCursor.getColumnIndexOrThrow(COLUMN_NAME_FIELD_TYPES))));

        return aCategory;
    }

    private String getFieldTypesFromBoolean(Category theCategory) {
        StringBuilder aStringBuilder = new StringBuilder();
        aStringBuilder.append("{");
        int i = 0;
        for (boolean aType : theCategory.getDefaultFieldVisibilities()) {
            if (i == 0) {
                aStringBuilder.append(aType);
            } else {
                aStringBuilder.append(",").append(aType);
            }

            i++;
        }

        aStringBuilder.append("}");

        return aStringBuilder.toString();
    }

    private boolean[] getFieldTypesToBoolean(String theString) {
        if (StringUtils.isNullOrEmpty(theString)) {
            return Category.defaultTemplateVisibilities;
        }

        String aTableValues = theString.replace("{", "").replace("}", "");
        String[] fieldValues = StringUtils.split(aTableValues, ",");
        boolean[] fieldTypes = new boolean[fieldValues.length];
        for (int i = 0; i < fieldTypes.length; i++) {
            fieldTypes[i] = Boolean.parseBoolean(fieldValues[i]);
        }

        return fieldTypes;
    }
}
