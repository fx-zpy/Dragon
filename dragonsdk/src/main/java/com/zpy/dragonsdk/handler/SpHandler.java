package com.zpy.dragonsdk.handler;

import android.content.Context;
import android.content.SharedPreferences;

import com.zpy.dragonsdk.inter.SpKey;

public class SpHandler implements ISpHandler {

    private static final byte[] instanceLock = new byte[0];

    private static final byte[] loginLock = new byte[0];

    private static ISpHandler instance;

    private Context context;

    private SharedPreferences preferences;

    private static final String SP_FILE_NAME = "HiCodeSP";

    private SpHandler(Context context) {
        this.context = context;
        this.preferences = this.context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
    }

    public static ISpHandler getInstance(Context context) {
        return newInstance(context);
    }

    private static ISpHandler newInstance(Context context) {
        synchronized (instanceLock) {
            if (instance == null) {
                instance = new SpHandler(context);
            }
            return instance;
        }
    }

    @Override
    public void setUsername(String username) {
        synchronized (loginLock) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(SpKey.LOGIN_USERNAME, username);
        }
    }

    @Override
    public String getUsrname() {
        synchronized (loginLock) {
            return preferences.getString(SpKey.LOGIN_USERNAME, "");
        }
    }

    @Override
    public void setTelephone(String telephone) {
        synchronized (loginLock) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(SpKey.LOGIN_TELEPHONE, telephone);
        }
    }

    @Override
    public String getTelephone() {
        synchronized (loginLock) {
            return preferences.getString(SpKey.LOGIN_TELEPHONE, "");
        }
    }

    @Override
    public void setPassword(String password) {
        synchronized (loginLock) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(SpKey.LOGIN_PASSWORD, password);
        }
    }

    @Override
    public String getPassword() {
        synchronized (loginLock) {
            return preferences.getString(SpKey.LOGIN_PASSWORD, "");
        }
    }

    @Override
    public void setLoginTime(long loginTime) {
        synchronized (loginLock) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putLong(SpKey.LOGIN_TIME, loginTime);
        }
    }

    @Override
    public long getLoginTime() {
        synchronized (loginLock) {
            return preferences.getLong(SpKey.LOGIN_TIME, 0L);
        }
    }
}
