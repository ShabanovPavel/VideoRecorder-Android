package com.shp.video_recorder;

import android.content.Context;
import android.content.SharedPreferences;


import java.util.ArrayList;

public class Settings {
    private static SharedPreferences mPreferences;

    public static final String NETWORK_VIDEOUPLAOD = "video_upload";
    public static final String NETWORK_VIDEOUPLAOD_WIFI_ONLY = "Только WiFi";
    public static final String NETWORK_VIDEOUPLAOD_ON = "Включено";
    public static final String NETWORK_VIDEOUPLAOD_OFF = "Отключено";

    public static final String VIDEO_QUALITY = "video_quality";
    public static final String VIDEO_QUALITY_LOW = "Низкое";
    public static final String VIDEO_QUALITY_HIGH = "Высокое";

    public static final String VIDEO_RESOLUTION = "video_resolution";
    public static final String VIDEO_RESOLUTION_DEFALUT = "480x320";

    public static final String VIDEO_FPS = "video_fps";
    public static final String VIDEO_FPS_DEFAULT = "15";

    public static final String VIDEO_BITRATE = "video_bitrate";
    public static final String VIDEO_BITRATE_DEFAULT = "600000";

    public static final String VIDEO_DURATION = "video_duration";
    public static final String VIDEO_DURATION_DEFAULT = "10";

    private static ArrayList<SharedPreferences.OnSharedPreferenceChangeListener> changeListeners = new ArrayList<>();

    public static void init(Context context) {
        mPreferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE);
        if (getString(VIDEO_RESOLUTION).isEmpty())
            putString(VIDEO_RESOLUTION, VIDEO_RESOLUTION_DEFALUT);
        if (getString(VIDEO_FPS).isEmpty())
            putString(VIDEO_FPS, VIDEO_FPS_DEFAULT);
        if (getString(VIDEO_BITRATE).isEmpty())
            putString(VIDEO_BITRATE, VIDEO_BITRATE_DEFAULT);
        if (getString(VIDEO_DURATION).isEmpty())
            putString(VIDEO_DURATION, VIDEO_DURATION_DEFAULT);
        if (getString(VIDEO_QUALITY).isEmpty())
            putString(VIDEO_QUALITY, VIDEO_QUALITY_LOW);
        if (getString(NETWORK_VIDEOUPLAOD).isEmpty())
            putString(NETWORK_VIDEOUPLAOD, NETWORK_VIDEOUPLAOD_WIFI_ONLY);
    }

    public static void resetToDefaults() {
        putString(VIDEO_RESOLUTION, VIDEO_RESOLUTION_DEFALUT);
        putString(VIDEO_FPS, VIDEO_FPS_DEFAULT);
        putString(VIDEO_QUALITY, VIDEO_QUALITY_LOW);
        putString(VIDEO_BITRATE, VIDEO_BITRATE_DEFAULT);
        putString(VIDEO_DURATION, VIDEO_DURATION_DEFAULT);
        putString(NETWORK_VIDEOUPLAOD, NETWORK_VIDEOUPLAOD_WIFI_ONLY);
    }

    public static SharedPreferences getPreferences() {
        return mPreferences;
    }

    private static void onChange(String key) {
        for (int i = 0; i < changeListeners.size();++i) {
            changeListeners.get(i).onSharedPreferenceChanged(mPreferences, key);
        }
    }

    public static void addOnChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        changeListeners.add(listener);
    }

    public static void removeOnChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        changeListeners.remove(listener);
    }

    public static void putBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
        onChange(key);
    }

    public static boolean getBoolean(String key) {
        return mPreferences.getBoolean(key, false);
    }

    public static void putString(String key, String value) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(key, value);
        editor.apply();
        onChange(key);
    }

    public static String getString(String key) {
        return mPreferences.getString(key, "");
    }

    public static boolean checkValue(String key, String value) {
        return getString(key).compareTo(value) == 0;
    }
}
