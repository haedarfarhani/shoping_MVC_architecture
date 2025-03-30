package com.heydar.simplemcv.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.heydar.simplemcv.MyApplication;

public class AppSharedPref {

    private static final SharedPreferences sharedPreferences = MyApplication.getContext().getSharedPreferences(Constants.SP_NAME, Context.MODE_PRIVATE);

    public static String getToken() {
        return (String) SPUtils.get(sharedPreferences, "access_token", "");
    }
    public static void setToken(String token) {
        SPUtils.put(sharedPreferences, "access_token", token, false);
    }

}
