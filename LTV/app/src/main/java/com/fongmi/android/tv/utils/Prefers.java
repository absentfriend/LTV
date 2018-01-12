package com.fongmi.android.tv.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.fongmi.android.tv.App;

public class Prefers {

    private static final String IP = "ip";
    private static final String SIZE = "size";
    private static final String DELAY = "delay";
    private static final String BACK_WAIT = "back_wait";
    private static final String PLAY_WAIT = "play_wait";

    private static SharedPreferences getPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(App.getInstance());
    }

    private static Integer getInt(String key, int defaultValue) {
        return getPreferences().getInt(key, defaultValue);
    }

    private static void putInt(String key, int value) {
        getPreferences().edit().putInt(key, value).apply();
    }

    private static Boolean getBoolean(String key) {
        return getPreferences().getBoolean(key, false);
    }

    private static void putBoolean(String key, boolean value) {
        getPreferences().edit().putBoolean(key, value).apply();
    }

    public static String getIp() {
        return getPreferences().getString(IP, "");
    }

    public static void putIp(String value) {
        getPreferences().edit().putString(IP, value).apply();
    }

    public static int getSize() {
        return getInt(SIZE, 0);
    }

    public static void putSize(int value) {
        putInt(SIZE, value);
    }

    static int getDelay() {
        return getInt(DELAY, 1);
    }

    static void putDelay(int value) {
        putInt(DELAY, value);
    }

    public static boolean isBackWait() {
        return getBoolean(BACK_WAIT);
    }

    static void putBackWait(boolean value) {
        putBoolean(BACK_WAIT, value);
    }

    public static boolean isPlayWait() {
        return getBoolean(PLAY_WAIT);
    }

    static void putPlayWait(boolean value) {
        putBoolean(PLAY_WAIT, value);
    }
}
