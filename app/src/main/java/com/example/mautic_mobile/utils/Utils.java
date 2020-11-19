package com.example.mautic_mobile.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mautic_mobile.R;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {
    public static final String SETTING_INFOS = "com.mautic_mobile.SETTING_INFOS";
    public static final String NAME = "NAME";
    public static final String PASSWORD = "PASSWORD";
    public static final String END_POINT = "END_POINT";

    public static String getDateFormat(Date date,Context context) {
        if (date != null) {
            DateFormat mediumDateFormatEN = DateFormat.getDateTimeInstance(
                    DateFormat.MEDIUM,
                    DateFormat.MEDIUM, new Locale(context.getString(R.string.language), context.getString(R.string.country)));
            return mediumDateFormatEN.format(date);
        }
        return "";
    }

    private static SharedPreferences getSettingsPreferences(Context activity) {
        SharedPreferences settings = activity.getSharedPreferences(SETTING_INFOS, 0);
        return settings;
    }

    public static String getUserNameFromPreferences(Context activity) {
        return getSettingsPreferences(activity).getString(NAME, "");
    }

    public static String getUserPswFromPreferences(Context activity) {
        return Encryption.decrypt(getSettingsPreferences(activity).getString(PASSWORD, ""));
    }

    public static String getUserEndPointFromPreferences(Context activity) {
        return getSettingsPreferences(activity).getString(END_POINT, "");
    }

    public static void editIdentifiersPreferences(Context activity,String name,String password,String endPoint) {
        getSettingsPreferences(activity).edit()
                .putString(NAME, name)
                .putString(PASSWORD, password)
                .putString(END_POINT, endPoint)
                .apply();
    }
}
