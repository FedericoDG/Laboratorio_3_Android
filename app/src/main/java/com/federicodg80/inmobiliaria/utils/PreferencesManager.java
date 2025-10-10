package com.federicodg80.inmobiliaria.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesManager {
    private static final String PREFS_NAME = "inmobiliaria_prefs";
    private static final String KEY_TOKEN = "token";

    private static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static void saveToken(Context context, String token) {
        getPreferences(context)
                .edit()
                .putString(KEY_TOKEN, token)
                .apply();
    }

    public static String getToken(Context context) {
        return getPreferences(context).getString(KEY_TOKEN, null);
    }

    public static void clearToken(Context context) {
        getPreferences(context)
                .edit()
                .remove(KEY_TOKEN)
                .apply();
    }

    public static boolean hasToken(Context context) {
        return getToken(context) != null;
    }
}
