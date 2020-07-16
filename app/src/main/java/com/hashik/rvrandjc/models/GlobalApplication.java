package com.hashik.rvrandjc.models;

import android.app.Application;

import com.hashik.rvrandjc.models.JSONDataModels.JSONData;

public class GlobalApplication extends Application {
    private static final String BASEURL = "https://raw.githubusercontent.com";
    private static JSONData userData;

    public static JSONData getUserData() {
        return userData;
    }


    /*private static void getDataFromSharedPreferences(){
        Gson gson = new Gson();
        String json = sp.getString("userdata", null);
        userData = gson.fromJson(json, JSONData.class);
    }*/

    public static void setUserData(JSONData userData) {
        GlobalApplication.userData = userData;
    }

    public static String getBaseurl() {
        return BASEURL;
    }
}
