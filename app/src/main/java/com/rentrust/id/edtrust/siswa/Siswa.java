package com.rentrust.id.edtrust.siswa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rentrust.id.edtrust.R;
import com.rentrust.id.edtrust.session.SessionManager;
import com.rentrust.id.edtrust.model.modelRoomSiswa;
import com.rentrust.id.edtrust.siswa.history.History;
import com.rentrust.id.edtrust.siswa.materi.Materi;
import com.rentrust.id.edtrust.siswa.nilai.Nilai;
import com.rentrust.id.edtrust.siswa.profile.Profile;
import com.rentrust.id.edtrust.siswa.room.MyRoom;
import com.rentrust.id.edtrust.siswa.tugas.Tugas;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class Siswa extends AppCompatActivity implements SiswaView {

    TextView welcome, txt_nisn, nama_room, nama_guru, jumlah_peserta;
    ImageView btn_profile;
    LinearLayout category, category_shimmer, box_materi, box_tugas, box_ujian, box_nilai, box_history, box_qa;
    RelativeLayout relativeCard, getRelativeCardShimmer;

    SessionManager sessionManager;

    SiswaPresenter presenter;

    String nama, nisn, name_room;
    int id_guru, id_room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siswa);

        txt_nisn = findViewById(R.id.txt_nisn);
        welcome = findViewById(R.id.welcome);
        nama_guru = findViewById(R.id.label_nama_guru);
        nama_room = findViewById(R.id.label_nama_room);
        jumlah_peserta = findViewById(R.id.label_jumlah_peserta);
        btn_profile = findViewById(R.id.profile);

        //Shimmer
        category = findViewById(R.id.recycler_category);
        category_shimmer = findViewById(R.id.recycler_category_shimmer);
        relativeCard = findViewById(R.id.relativeCard);
        getRelativeCardShimmer = findViewById(R.id.relativeCardShimmer);

        //Box
        box_materi = findViewById(R.id.box_materi);
        box_tugas = findViewById(R.id.box_tugas);
        box_ujian = findViewById(R.id.box_ujian);
        box_nilai = findViewById(R.id.box_nilai);
        box_history = findViewById(R.id.box_history);
        box_qa = findViewById(R.id.box_qa);

        sessionManager = new SessionManager(this);
        sessionManager.checkSiswa();

        HashMap<String, String> user = sessionManager.getMainSesi();
        nama = user.get(sessionManager.NAMA);
        nisn = user.get(sessionManager.NISN);

        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if (timeOfDay >= 0 && timeOfDay < 10) {
            welcome.setText("Selamat Pagi, " + nama);
        } else if (timeOfDay >= 10 && timeOfDay < 15) {
            welcome.setText("Selamat Siang, " + nama);
        } else if (timeOfDay >= 15 && timeOfDay < 18) {
            welcome.setText("Selamat Sore, " + nama);
        } else if (timeOfDay >= 18 && timeOfDay < 24) {
            welcome.setText("Selamat Malam, " + nama);
        }

        txt_nisn.setText("NISN : "+nisn);

        presenter = new SiswaPresenter(this);
        presenter.getInfoRoom(nisn);

        relativeCard.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MyRoom.class);
            startActivity(intent);
        });

        box_tugas.setOnClickListener(v -> {
            Intent t = new Intent(Siswa.this, Tugas.class);
            t.putExtra("id_guru", id_guru);
            t.putExtra("id_room", id_room);
            startActivity(t);
        });

        box_nilai.setOnClickListener(v -> {
            Intent n = new Intent(Siswa.this, Nilai.class);
            startActivity(n);
        });

        box_history.setOnClickListener(v -> {
            Intent h = new Intent(Siswa.this, History.class);
            startActivity(h);
        });

        btn_profile.setOnClickListener(v -> {
            Intent f = new Intent(Siswa.this, Profile.class);
            startActivity(f);
        });

        box_materi.setOnClickListener(v -> {
            Intent m = new Intent(Siswa.this, Materi.class);
            m.putExtra("id_guru", id_guru);
            m.putExtra("nama_room", name_room);
            startActivity(m);
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.getInfoRoom(nisn);

        sessionManager.checkSiswa();

        HashMap<String, String> user = sessionManager.getMainSesi();
        nama = user.get(sessionManager.NAMA);

        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if (timeOfDay >= 0 && timeOfDay < 10) {
            welcome.setText("Selamat Pagi, " + nama);
        } else if (timeOfDay >= 10 && timeOfDay < 15) {
            welcome.setText("Selamat Siang, " + nama);
        } else if (timeOfDay >= 15 && timeOfDay < 18) {
            welcome.setText("Selamat Sore, " + nama);
        } else if (timeOfDay >= 18 && timeOfDay < 24) {
            welcome.setText("Selamat Malam, " + nama);
        }
    }

    @Override
    public void showLoading() {
        category.setVisibility(View.GONE);
        category_shimmer.setVisibility(View.VISIBLE);
        relativeCard.setVisibility(View.GONE);
        getRelativeCardShimmer.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        category.setVisibility(View.VISIBLE);
        category_shimmer.setVisibility(View.GONE);
        relativeCard.setVisibility(View.VISIBLE);
        getRelativeCardShimmer.setVisibility(View.GONE);
    }

    @Override
    public void onGetRoomSiswa(List<modelRoomSiswa> roomSiswas) {

        if (roomSiswas.isEmpty()) {
            nama_room.setText("Anda belum bergabung dalam room!");
        } else {
            nama_room.setText(roomSiswas.get(0).getNama_room());
            nama_guru.setText("Nama guru : " +roomSiswas.get(0).getNama_guru());
            jumlah_peserta.setText("Jumlah peserta : " +roomSiswas.get(0).getJumlah_peserta());
            id_guru = roomSiswas.get(0).getId_guru();
            id_room = roomSiswas.get(0).getId_room();
            name_room = roomSiswas.get(0).getNama_room();
        }

    }

    @Override
    public void onRequestSuccess(String message) {

    }

    @Override
    public void onRequestError(String message) {

    }
}
