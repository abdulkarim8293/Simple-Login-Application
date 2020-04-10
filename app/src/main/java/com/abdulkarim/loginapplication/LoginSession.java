package com.abdulkarim.loginapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

public class LoginSession {

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private int PRIVATE_MODE = 0;
    private Context context;

    public static final String PREFER_NAME = "UserLoginSession";
    public static final String IS_USER_LOGIN = "IsUserLoggedIn";
    public static final String KEY_USER_NAME = "user_name";
    public static final String KEY_USER_PASSWORD = "password";

    public LoginSession(Context context) {
        this.context = context;
        sp = context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = sp.edit();
    }

    public void createLoginSession(String userName, String password) {
        editor.putString(KEY_USER_NAME, (String) userName);
        editor.putString(KEY_USER_PASSWORD, (String) password);
        editor.putBoolean(IS_USER_LOGIN, true);
        editor.commit();
    }

    public void logoutUser() {
        editor.clear();
        editor.commit();
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public boolean isUserLoggedIn() {
        return sp.getBoolean(IS_USER_LOGIN, false);
    }

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_USER_NAME, sp.getString(KEY_USER_NAME, null));
        user.put(KEY_USER_PASSWORD, sp.getString(KEY_USER_PASSWORD, null));
        return user;
    }

}
