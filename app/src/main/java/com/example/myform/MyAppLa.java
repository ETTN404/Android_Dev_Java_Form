package com.example.myform;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;

import java.util.Locale;

public class MyAppLa extends Application {
    private SharedPreferences prefs;
    @Override
    public void onCreate() {
        super.onCreate();
    }
    public static Context Language(Context context,String language_code){
        Locale locale=new Locale(language_code);
        Locale.setDefault(locale);
        Configuration config=new Configuration();
        config.setLocale(locale);
        return context.createConfigurationContext(config);
    }
    public void store_lan(String value,String Key,Context context){
        prefs= PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString(Key,value).apply();
        Language(context,get_lan(Key,context));
    }
    public String get_lan(String key,Context context){
        prefs=PreferenceManager.getDefaultSharedPreferences(context);
        String a=prefs.getString(key,"en");
        return a;
    }

}