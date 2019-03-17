package com.hashik.rvrandjc.models;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.hashik.rvrandjc.models.JSONDataModels.JSONData;
import com.squareup.leakcanary.LeakCanary;

public class GlobalApplication extends Application {
    private static final String BASEURL = "https://pastebin.com";
    private static JSONData userData;

    public static SharedPreferences getSp() {
        return sp;
    }

    private static SharedPreferences sp;

    public GlobalApplication(){
        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    }

    public void setUserData(JSONData userData) {
        GlobalApplication.userData = userData;
        storeToSharedPreferences();
    }

    private void storeToSharedPreferences() {
        SharedPreferences.Editor editor = sp.edit();
        Gson gson = new Gson();
        String json = gson.toJson(userData);
        editor.putString("userdata", json);
        editor.apply();
    }

    private static void getDataFromSharedPreferences(){
        Gson gson = new Gson();
        String json = sp.getString("userdata", null);
        userData = gson.fromJson(json, JSONData.class);
    }

    public static JSONData getUserData() {
        if (userData == null){
            getDataFromSharedPreferences();
            return userData;
        }
        return userData;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...
    }

    public static String getBaseurl(){
        return BASEURL;
    }
}
