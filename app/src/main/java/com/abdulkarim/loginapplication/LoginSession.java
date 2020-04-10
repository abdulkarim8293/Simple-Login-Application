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

    public void createUserLoginSession(String userName, String password) {
        editor.putString(KEY_USER_NAME, (String) userName);
        editor.putString(KEY_USER_PASSWORD, (String) password);
        editor.putBoolean(IS_USER_LOGIN, true);
        editor.commit();
    }

    public void createUser(String userName, String password) {
        editor.putString(KEY_USER_NAME, (String) userName);
        editor.putString(KEY_USER_PASSWORD, (String) password);
        editor.commit();
    }

    public boolean checkLogin() {
        // Check login status
        if (!this.isUserLoggedIn()) {

            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(context, LoginActivity.class);

            // Closing all the Activities from stack
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            context.startActivity(i);

            return true;
        }
        return false;
    }

    /**
     * Get stored session data
     */
    public HashMap<String, String> getUserDetails() {

        //Use hashmap to store user credentials
        HashMap<String, String> user = new HashMap<String, String>();

        // user name
        user.put(KEY_USER_NAME, sp.getString(KEY_USER_NAME, null));

        // user email id
        user.put(KEY_USER_PASSWORD, sp.getString(KEY_USER_PASSWORD, null));

        // return user
        return user;
    }

    /**
     * Clear session details
     */
    public void logoutUser() {

        // Clearing all user data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to MainActivity
        Intent i = new Intent(context, LoginActivity.class);

        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        context.startActivity(i);
    }

    // Check for login
    public boolean isUserLoggedIn() {
        return sp.getBoolean(IS_USER_LOGIN, false);
    }
}
