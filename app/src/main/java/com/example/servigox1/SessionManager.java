package com.example.servigox1;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    //Session names
    public static final String SESSION_USERSESSION = "userLoginSession";
    public static final String SESSION_REMEMBER_ME = "rememberMeSession";

    //USERSESSION variables
    private static final String IS_LOGIN = "isLogedIn";

    public static final String KEY_FULLNAME = "fullName";
    public static final String KEY_CONTACT = "contactNo";
    public static final String KEY_EMAIL = "emailID";
    public static final String KEY_CITY = "city";
    public static final String KEY_PASSWORD = "password";

    //Remember Me variables

    private static final String IS_REMEMBER_ME = "isRememberMe";
    public static final String SESSION_CONTACT = "userContactNo";
    public static final String SESSION_PASSWORD = "sessionPassword";




    public SessionManager(Context context, String sessionName) {
        this.context = context;
        sharedPreferences = this.context.getSharedPreferences(sessionName, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void createLoginSession(String contact,String password,String city,String email,String fullName){

        editor.putBoolean(IS_LOGIN,true);

        editor.putString(KEY_PASSWORD,password);
        editor.putString(KEY_CONTACT,contact);
        editor.putString(KEY_CITY,city);
        editor.putString(KEY_EMAIL,email);
        editor.putString(KEY_FULLNAME,fullName);


        editor.commit();
    }

    public HashMap<String,String> getUserDetailsFromSession(){
        HashMap<String, String> userData = new HashMap<String,String>();

        userData.put(KEY_CONTACT,sharedPreferences.getString(KEY_CONTACT,null));
        userData.put(KEY_EMAIL,sharedPreferences.getString(KEY_EMAIL,null));
        userData.put(KEY_CITY,sharedPreferences.getString(KEY_CITY,null));
        userData.put(KEY_FULLNAME,sharedPreferences.getString(KEY_FULLNAME,null));


        return userData;
    }

    public void createRememberMeSession(String contact, String password){

        editor.putBoolean(IS_REMEMBER_ME, true);

        editor.putString(SESSION_CONTACT,contact);
        editor.putString(SESSION_PASSWORD,password);

        editor.commit();
    }

    public HashMap<String,String> getRememberMeDetailsFromSession(){
        HashMap<String, String> userData = new HashMap<String,String>();

        userData.put(SESSION_CONTACT,sharedPreferences.getString(SESSION_CONTACT,null));
        userData.put(SESSION_PASSWORD,sharedPreferences.getString(SESSION_PASSWORD,null));

        return userData;
    }


    public boolean checkLogin(){
        if (sharedPreferences.getBoolean(IS_LOGIN, false)){
            return true;
        } else
            return false;
    }

    public boolean checkRemember(){
        if (sharedPreferences.getBoolean(IS_REMEMBER_ME, false)){
            return true;
        } else
        return false;
    }

    public void logOutUserFromSession(){
        editor.clear();
        editor.commit();
    }
}
