package com.trei.news.Utils;


import android.content.Context;
import android.content.SharedPreferences;

public class AppSharePreference {

    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;
    private String APP_SHARED_PREFS;


    public AppSharePreference(Context mContext) {
        this.sharedPreferences = mContext.getSharedPreferences(APP_SHARED_PREFS, Context.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
        this.APP_SHARED_PREFS = "News";
    }

    public void clearAllPreferences() {
        editor.clear();
        editor.commit();
    }

    public void clearPreferences(String key) {
        editor.remove(key);
        editor.apply();

    }

    public String getCountryCode() {
        return sharedPreferences.getString("countryCode","in");
    }

    public void setCountryCode(String countryCode) {
        editor.putString("countryCode", countryCode);
        editor.commit();
    }
}
