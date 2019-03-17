package com.hashik.rvrandjc.models;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

public class GlobalApplication extends Application {
    private static final String BASEURL = "https://pastebin.com";

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
