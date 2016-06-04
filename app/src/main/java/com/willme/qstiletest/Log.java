package com.willme.qstiletest;

import android.content.Intent;

/**
 * Created by Wen on 5/21/16.
 */
public class Log {

    public static final String TAG = "QS_TILE_TEST";
    public static final String ACTION_LOG = "com.willme.qstiletest.ACTION_LOG";
    public static final String EXTRA_MSG = "msg";


    public static void log(String msg){
        android.util.Log.d(TAG, msg);
        Intent intent = new Intent(ACTION_LOG);
        intent.putExtra(EXTRA_MSG, msg);
        TestApp.getInstance().sendBroadcast(intent);
    }

}
