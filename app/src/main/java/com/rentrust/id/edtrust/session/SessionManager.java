package com.rentrust.id.edtrust.session;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.rentrust.id.edtrust.Login;
import com.rentrust.id.edtrust.MainActivity;
import com.rentrust.id.edtrust.siswa.Siswa;
import com.rentrust.id.edtrust.siswa.profile.Profile;

import java.util.HashMap;

public class SessionManager {

    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "LOGIN";
    private static final String LOGIN = "IS_LOGIN";
    public static final String NAMA = "NAMA";
    public static final String USERNAME = "USERNAME";
    public static final String NISN = "NISN";

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void createSession(String nama, String username, String nisn){
        editor.putBoolean(LOGIN, true);
        editor.putString(NAMA, nama);
        editor.putString(USERNAME, username);
        editor.putString(NISN, nisn);
        editor.apply();
    }

    public boolean isLoggin(){
        return sharedPreferences.getBoolean(LOGIN, false);
    }

    public void checkLogin(){
        if (!this.isLoggin()){
            Intent i = new Intent(context, Login.class);
            context.startActivity(i);
            ((MainActivity) context).finish();
        }
    }

    public void checkSiswa(){
        if (!this.isLoggin()){
            Intent i = new Intent(context, Login.class);
            context.startActivity(i);
            ((Siswa) context).finish();
        }
    }

    public HashMap<String, String> getMainSesi(){

        HashMap<String, String> user = new HashMap<>();
        user.put(NAMA, sharedPreferences.getString(NAMA, null));
        user.put(USERNAME, sharedPreferences.getString(USERNAME, null));
        user.put(NISN, sharedPreferences.getString(NISN, null));

        return user;
    }

    public void logoutSiswa(){

        editor.clear();
        editor.commit();
        Intent i = new Intent(context, Login.class);
        Toast.makeText(context,"Logout success !", Toast.LENGTH_SHORT).show();
        context.startActivity(i);
        ((Profile) context).finish();
    }



}
