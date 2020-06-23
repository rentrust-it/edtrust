package com.rentrust.id.edtrust.siswa.materi;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import com.rentrust.id.edtrust.R;
import com.rentrust.id.edtrust.siswa.materi.ui.SectionsPagerAdapter;

public class Materi extends AppCompatActivity {

    String nama_room;
    int id_guru;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi);

        Intent intent = getIntent();
        id_guru = intent.getIntExtra("id_guru", 0);
        nama_room = intent.getStringExtra("nama_room");
        Log.i("ez",id_guru+" "+nama_room);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, nama_room, id_guru, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

//        Intent intent = getIntent();
//        id_guru = intent.getIntExtra("id_guru", 0);
//        nama_room = intent.getStringExtra("nama_room");
//
//        FragFILE fragFILE = FragFILE.newInstance(nama_room, id_guru);
//        getSupportFragmentManager().beginTransaction().replace(R.id.view_pager, fragFILE).commit();

    }

}