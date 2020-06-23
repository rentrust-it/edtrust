package com.rentrust.id.edtrust;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.rentrust.id.edtrust.session.SessionManager;
import com.rentrust.id.edtrust.siswa.Siswa;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManager =new SessionManager(this);
//        sessionManager.checkLogin();

        HashMap<String, String> user = sessionManager.getMainSesi();
        String username = user.get(sessionManager.USERNAME);

        if (username == null) {
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(MainActivity.this, Siswa.class);
            startActivity(intent);
            finish();
        }



    }
}
