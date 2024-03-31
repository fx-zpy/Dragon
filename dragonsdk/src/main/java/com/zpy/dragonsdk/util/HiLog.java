package com.zpy.dragonsdk.util;

import android.util.Log;

public class HiLog {

    static String flag = "HiCode.";

    public static void i(String TAG, String msg, Object... args) {
        String Msg = String.format(msg, args);
        Log.i(flag + TAG, Msg);
    }

    public static void d(String TAG, String msg, Object... args) {
        String Msg = String.format(msg, args);
        Log.d(flag + TAG, Msg);
    }

    public static void e(String TAG, String msg, Object... args) {
        String Msg = String.format(msg, args);
        Log.e(flag + TAG, Msg);
    }

    public static void w(String TAG, String msg, Object... args) {
        String Msg = String.format(msg, args);
        Log.w(flag + TAG, Msg);
    }

    public static void v(String TAG, String msg, Object... args) {
        String Msg = String.format(msg, args);
        Log.v(flag + TAG, Msg);
    }

}
