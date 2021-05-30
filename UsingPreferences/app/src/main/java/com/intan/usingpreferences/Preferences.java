package com.intan.usingpreferences;

import android.content.Context; //memberikan akses informasi atas application state
import android.content.SharedPreferences; //sebagai tempat penyimpanan data sederhana
import android.preference.PreferenceManager; //untuk mengedit preferens

public class Preferences {
    /* Sebagai tempat penyimpanan data bertipe String dan setiap data memiliki kata kunci yang berbeda */
    static final String KEY_USER_TEREGISTER ="user", KEY_PASS_TEREGISTER ="pass"; //untuk menyimpan data user dan password
    static final String KEY_USERNAME_SEDANG_LOGIN ="Username_logged_in"; //untuk mengecek data user yang tersimpan
    static final String KEY_STATUS_SEDANG_LOGIN ="Status_logged_in"; //untuk mengecek status data yang tersimpan

    /* Pendeklarasian Shared Preferences yang berdasarkan parameter context */
    //untuk membuat file Shared Preference dengan parameter secara spesifik menggunakan Context untuk mengakses file pada aplikasi
    private static SharedPreferences getSharedPreference(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    /* Digunakan untuk edit Preferences dan mengubah data dengan parameter username */
    public static void setRegisteredUser(Context context, String username){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_USER_TEREGISTER, username);
        editor.apply();
    }

    /* Digunakan untuk mengembalikan nilai KEY_USER_TEREGISTER dengan tipe data string */
    public static String getRegisteredUser(Context context){
        return getSharedPreference(context).getString(KEY_USER_TEREGISTER, "");
    }

    /* Digunakan untuk edit Preferences dan mengubah data dengan parameter password */
    public static void setRegisteredPass(Context context, String password){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_PASS_TEREGISTER, password);
        editor.apply();
    }

    /* Digunakan untuk mengembalikan nilai KEY_PASS_TEREGISTER dengan tipe data string */
    public static String getRegisteredPass(Context context){
        return getSharedPreference(context).getString(KEY_PASS_TEREGISTER, "");
    }

    /* Digunakan untuk edit Preferences dan mengubah data dengan parameter username */
    public static void setLoggedInUser(Context context, String username){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_USERNAME_SEDANG_LOGIN, username);
        editor.apply();
    }

    /* Digunakan untuk mengembalikan nilai KEY_USERNAME_SEDANG_LOGIN dengan tipe data string */
    public static String getLoggedInUser(Context context){
        return getSharedPreference(context).getString(KEY_USERNAME_SEDANG_LOGIN, "");
    }

    /* Digunakan untuk edit Preferences dan mengubah data dengan parameter status */
    public static void setLoggedInStatus(Context context, boolean status){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putBoolean(KEY_STATUS_SEDANG_LOGIN, status);
        editor.apply();
    }

    /* Digunakan untuk mengembalikan nilai KEY_STATUS_SEDANG_LOGIN dengan tipe data boolean */
    public static boolean getLoggedInStatus(Context context){
        return getSharedPreference(context).getBoolean(KEY_STATUS_SEDANG_LOGIN, false);
    }

    /* Deklarasi edit Preferences dan menghapus data, sehingga menjadikannya bernilai default
     * khusus data yang memiliki key KEY_USERNAME_SEDANG_LOGIN dan KEY_STATUS_SEDANG_LOGIN */
     public static void clearLoggedInUser(Context context){
         SharedPreferences.Editor editor = getSharedPreference(context).edit();
         editor.remove(KEY_USERNAME_SEDANG_LOGIN);
         editor.remove(KEY_STATUS_SEDANG_LOGIN);
         editor.apply();
     }
}
