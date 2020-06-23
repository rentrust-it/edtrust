package com.rentrust.id.edtrust.siswa.tugas.worker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.rentrust.id.edtrust.R;
import com.rentrust.id.edtrust.model.modelPGanda;
import com.rentrust.id.edtrust.model.modelSoal;
import com.rentrust.id.edtrust.session.SessionManager;

import java.util.HashMap;
import java.util.List;

public class TugasSelesai extends AppCompatActivity implements WorkerView{

    TextView  txt_nama_siswa, txt_nisn, txt_score;
    Button confirm;
    ScrollView no_shimmer;
    ShimmerFrameLayout shimmer;

    SessionManager sessionManager;

    WorkerPresenter presenter;

    String nama_soal, nama, nisn;
    int nilai, id_soal, id_guru;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tugas_selesai);

        txt_nama_siswa = findViewById(R.id.namaSiswa);
        txt_nisn = findViewById(R.id.nisn);
        txt_score = findViewById(R.id.nilai);
        confirm = findViewById(R.id.confirm);
        no_shimmer = findViewById(R.id.no_shimmer);
        shimmer = findViewById(R.id.shimmer);

        sessionManager = new SessionManager(this);
        sessionManager.checkSiswa();

        HashMap<String, String> user = sessionManager.getMainSesi();
        nama = user.get(sessionManager.NAMA);
        nisn = user.get(sessionManager.NISN);

        presenter = new WorkerPresenter(this);

        Intent intent = getIntent();
        id_soal = intent.getIntExtra("id_soal", 0);
        id_guru = intent.getIntExtra("id_guru", 0);
        nilai = intent.getIntExtra("nilai", 0);

        txt_nama_siswa.setText(nama);
        txt_nisn.setText(nisn);
        txt_score.setText("SCORE ANDA : " + nilai);


        presenter.addNilai(nisn, id_guru, id_soal, nilai);

        confirm.setOnClickListener(v -> {
            finish();
        });


    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitByBackKey();

            //moveTaskToBack(false);

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void exitByBackKey() {

        Toast.makeText(this, "Silahkan menekan tombol Kembali ke beranda yang sudah disediakan.", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showLoading() {
        shimmer.setVisibility(View.VISIBLE);
        no_shimmer.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        shimmer.setVisibility(View.GONE);
        no_shimmer.setVisibility(View.VISIBLE);
    }

    @Override
    public void onGetSoal(List<modelPGanda> gandas) {

    }

    @Override
    public void onRequestSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
    }

    @Override
    public void onRequestError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
