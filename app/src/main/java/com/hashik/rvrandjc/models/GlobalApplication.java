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


    public static void setUserData(JSONData userData) {
        GlobalApplication.userData = userData;
    }


    /*private static void getDataFromSharedPreferences(){
        Gson gson = new Gson();
        String json = sp.getString("userdata", null);
        userData = gson.fromJson(json, JSONData.class);
    }*/

    public static JSONData getUserData() {
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
