package com.blmsr.manager.dao.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.blmsr.manager.dao.SqliteController;
import com.blmsr.manager.dao.UserService;
import com.blmsr.manager.db.UserTableContract;
import com.blmsr.manager.models.Category;
import com.blmsr.manager.models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LakshmiMadhav on 8/5/2015.
 */
public class UserServiceImpl implements UserService,UserTableContract {
    private SqliteController sqliteController;

    public UserServiceImpl(SqliteController sqliteController) {
        this.sqliteController = sqliteController;
    }

    @Override
    public long save(User theEntity) {
        SQLiteDatabase database = sqliteController.getWritableDatabase();
        ContentValues values= getContentValues(theEntity);

        long aRows = database.insert(TABLE_NAME, null, values);
        database.close();

        return aRows;
    }

    @Override
    public long update(User theEntity) {
        SQLiteDatabase database = sqliteController.getWritableDatabase();
        ContentValues values = getContentValues(theEntity);

        // Which row to update, based on the ID
        String selection = _ID + "= ?" ;
        String[] selectionArgs = { String.valueOf(theEntity.getId()) };

        int count = database.update(TABLE_NAME, values, selection, selectionArgs);
        database.close();

        return count;
    }

    @Override
    public long delete(User theEntity) {
        SQLiteDatabase database = sqliteController.getWritableDatabase();

        // Which row to update, based on the ID
        String selection = _ID + "= ?" ;
        String[] selectionArgs = { String.valueOf(theEntity.getId()) };

        // Issue SQL statement.
        int count = database.delete(TABLE_NAME, selection, selectionArgs);
        database.close();

        return count;
    }

    @Override
    public User getPassword(String thePassword) {
        SQLiteDatabase database = sqliteController.getReadableDatabase();
        String[] selectionArgs = { thePassword };
        // Which row to update, based on the ID
        String selection = COLUMN_NAME_PASSWORD + "= ?" ;
        Cursor cursor = database.query(
                TABLE_NAME,  // The table to query
                SQL_USER_PROJECTION,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        User anUser = null;
        if (cursor.moveToFirst()) {
            anUser = getModel(cursor);
        }
        database.close();
        return anUser;
    }

    private ContentValues getContentValues(User theUser)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_PASSWORD,theUser.getPassword());
        return values;
    }

    private User getModel(Cursor theCursor) {
        User anUser = new User();
        anUser.setId(theCursor.getInt(theCursor.getColumnIndexOrThrow(_ID)));
        anUser.setPassword(theCursor.getString(theCursor.getColumnIndexOrThrow(COLUMN_NAME_PASSWORD)));
        return anUser;
    }

    @Override
    public List<User> getAllPasswords()
    {
        ArrayList<User> categoryList = new ArrayList<User>();
        SQLiteDatabase database = sqliteController.getReadableDatabase();

        Cursor cursor = database.query(
                TABLE_NAME,  // The table to query
                SQL_USER_PROJECTION,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        if (cursor.moveToFirst()) {
            do {
                User aCategory = getModel(cursor);
                categoryList.add(aCategory);
            } while (cursor.moveToNext());
        }
        // return contact list
        return categoryList;
    }
}

