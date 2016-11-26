package com.example.helena.gamebook;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.preference.PreferenceManager;

import java.util.Locale;

/**
 * Created by Stéphanie Pinto on 26.11.2016.
 */

public class LocaleHelper {

    private static final String SELECTED_LANG = "Locale.Helper.Selected.Language";

    public static void OnCreate(Context context){
        String lang = getPersistedData(context, Locale.getDefault().getLanguage());
        setLocale(context,lang);
    }
    public static void OnCreate(Context context, String defaultLanguage){
        String lang = getPersistedData(context, defaultLanguage);
        setLocale(context,lang);
    }

    public static void setLocale(Context context, String lang) {
        persist(context,lang);
        updateResources(context,lang);
    }
    private static void persist(Context context, String lang){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(SELECTED_LANG,lang);
        editor.apply();
    }

    private static void updateResources(Context context, String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Resources resources = context.getResources();
        Configuration config = resources.getConfiguration();
        config.locale = locale;
        resources.updateConfiguration(config,resources.getDisplayMetrics());
    }

    private static String getPersistedData(Context context, String language) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(SELECTED_LANG,language);
    }
    public static String getLanguage(Context context){
        return getPersistedData(context,Locale.getDefault().getLanguage());
    }

}
