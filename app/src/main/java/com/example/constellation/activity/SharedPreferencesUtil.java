package com.example.constellation.activity;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtil {
    private static final String DEFAULT_NAME = "user_info";
    public static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(DEFAULT_NAME, Context.MODE_PRIVATE);
    }
    public static void putString(Context context, String key, String value) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        sharedPreferences.edit().putString(key, value).apply();
    }
    public static String getString(Context context, String key, String defaultValue) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        return sharedPreferences.getString(key, defaultValue);
    }
    public static boolean getBoolean(Context context, String key) {
        return getSharedPreferences(context).getBoolean(key, false);
    }
    public static void setBoolean(Context context, String key, boolean value) {
        getSharedPreferences(context).edit().putBoolean(key, value).apply();
    }
}
