package com.robinson.anyrentalapp.Helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.robinson.anyrentalapp.BLL.UserBLL;
import com.robinson.anyrentalapp.Model.User;
import com.robinson.anyrentalapp.R;

public class UserSession {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static final String IS_USER_LOGGED_IN = "IS_USER_LOGGED_IN";
    private static final String AUTH_USER = "LOGGED_IN_USER";
    private static final String TOKEN = "TOKEN";

    public UserSession(Context context) {
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.user_session), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void startSession(User user) {

        String userJson = new Gson().toJson(user);

        editor.putBoolean(IS_USER_LOGGED_IN, true);
        editor.putString(TOKEN, UserBLL.token);
        editor.putString(AUTH_USER, userJson);
        editor.commit();
    }

    public boolean isActive() {
        return sharedPreferences.getBoolean(IS_USER_LOGGED_IN, false);
    }

    public String getToken() {
        String token = sharedPreferences.getString(TOKEN, "");
        return token;
    }

    public User getUser() {

        String userJson = sharedPreferences.getString(AUTH_USER, "");
        User user = new Gson().fromJson(userJson, User.class);

        return user;
    }

    public void endSession() {

        editor.putBoolean(IS_USER_LOGGED_IN, false);
        editor.remove(AUTH_USER);
        editor.remove(TOKEN);
        editor.commit();
    }
}
