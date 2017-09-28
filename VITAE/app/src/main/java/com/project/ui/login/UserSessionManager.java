package com.project.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.HashMap;

public class UserSessionManager {

    SharedPreferences pref;
    Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;

    private static final String PREFER_NAME = "VitaeUserSession";
    private static final String IS_USER_LOGIN = "IsUserLoggedIn";
    public static final String KEY_USERNAME = "name";
    public static final String KEY_PASSWORD = "email";

    public UserSessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createUserLoginSession(String name, String email){
        editor.putBoolean(IS_USER_LOGIN, true);
        editor.putString( KEY_USERNAME, name);
        editor.putString( KEY_PASSWORD, email);
        editor.commit();
    }

    public boolean checkLogin(){
        if(!this.isUserLoggedIn()){
            Intent i = new Intent(_context, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
            return true;
        }
        return false;
    }

    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put( KEY_USERNAME, pref.getString( KEY_USERNAME, null));
        user.put( KEY_PASSWORD, pref.getString( KEY_PASSWORD, null));
        return user;
    }

    public void logoutUser(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(_context, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);
    }

    public boolean isUserLoggedIn(){
        return pref.getBoolean(IS_USER_LOGIN, false);
    }
}

