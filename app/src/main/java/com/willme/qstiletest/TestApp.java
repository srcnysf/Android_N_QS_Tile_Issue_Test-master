package com.willme.qstiletest;

import android.app.Application;

/**
 * Created by Wen on 5/21/16.
 */
public class TestApp extends Application {

    private static TestApp sInstance = null;

    public static TestApp getInstance(){
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    @Override
    public void onTerminate() {
        sInstance = null;
        super.onTerminate();
    }
}
