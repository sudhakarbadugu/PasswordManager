package com.blmsr.manager.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.blmsr.manager.dao.SqliteController;

import java.util.ArrayList;

/**
 * Created by LakshmiMadhav on 7/29/2015.
 */
public class PreferencesUtil {
    private static final String PREFERENCE_KEY = "PasswordManager";

    public static SharedPreferences getSharedPreferences(Context theContext) {
        SharedPreferences sharedPreferences = theContext.getSharedPreferences(
                PREFERENCE_KEY, Context.MODE_PRIVATE);

        return sharedPreferences;
    }
}
