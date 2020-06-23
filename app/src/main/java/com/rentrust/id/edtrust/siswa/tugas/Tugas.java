package com.rentrust.id.edtrust.siswa.tugas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.rentrust.id.edtrust.R;
import com.rentrust.id.edtrust.model.modelNilai;
import com.rentrust.id.edtrust.session.SessionManager;
import com.rentrust.id.edtrust.model.modelSoal;
import com.rentrust.id.edtrust.siswa.tugas.worker.TugasPage;

import java.util.HashMap;
import java.util.List;

public class Tugas extends AppCompatActivity implements TugasView {

    SessionManager sessionManager;
    Dialog myDialog;

    private RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefresh;

    TugasPresenter presenter;
    TugasAdapter adapter;
    TugasAdapter.ItemClickListener itemClickListener;
    List<modelSoal> soal;

    String nama_siswa, nisn, nama_guru, type_soal, nama_soal;
    int id_guru, id_room, id_soal, jumlah_soal, durasi, score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tugas);
        myDialog = new Dialog(this);

        recyclerView = findViewById(R.id.recyclerView);
        swipeRefresh = findViewById(R.id.swipe_refresh);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        id_guru = intent.getIntExtra("id_guru", 0);
        id_room = intent.getIntExtra("id_room", 0);

        sessionManager = new SessionManager(this);

        HashMap<String, String> user = sessionManager.getMainSesi();
        nama_siswa = user.get(sessionManager.NAMA);
        nisn = user.get(sessionManager.NISN);

        presenter = new TugasPresenter(this);
        presenter.getTugas(id_room, id_guru);
        presenter.cekTugas(id_soal, nisn);

        swipeRefresh.setOnRefreshListener(
                () -> presenter.getTugas(id_room, id_guru)
        );

        itemClickListener = ((view, position) -> {

            id_soal = soal.get(position).getId_soal();
            nama_guru = soal.get(position).getNama_guru();
            type_soal = soal.get(position).getType_soal();
            jumlah_soal = soal.get(position).getJumlah_soal();
            nama_soal = soal.get(position).getNama_soal();
            durasi = soal.get(position).getDurasi();
            score = soal.get(position).getScore();

            myDialog.setContentView(R.layout.popup_putkunci);

            Button btn_join;
            EditText et_kunci;
            TextView close, txt_nama_soal;
            ProgressBar loading;

            btn_join = myDialog.findViewById(R.id.join);
            et_kunci = myDialog.findViewById(R.id.kunci);
            txt_nama_soal = myDialog.findViewById(R.id.txtNamaSoal);
            close = myDialog.findViewById(R.id.close);
            loading = myDialog.findViewById(R.id.loading);

            txt_nama_soal.setText(nama_soal);
            et_kunci.setFilters(new InputFilter[] {new InputFilter.AllCaps()});

            btn_join.setOnClickListener(views -> {

                String kunci = et_kunci.getText().toString().trim();

                if (kunci.isEmpty()) {
                    et_kunci.setError("Kunci tugas tidak boleh kosong");
                } else {

                    loading.setVisibility(View.VISIBLE);
                    btn_join.setVisibility(View.GONE);

                    // Hiding keyboard
                    View vo = myDialog.getCurrentFocus();
                    if (vo != null) {
                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(vo.getWindowToken(), 0);
                    }

                    presenter.joinTugas(nisn, kunci, id_soal);
                }

            });

            close.setOnClickListener(views -> myDialog.dismiss());
            myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            myDialog.show();

        });

    }

    @Override
    public void showLoading() {
        swipeRefresh.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void onGetTugas(List<modelSoal> soals) {
        adapter = new TugasAdapter(Tugas.this, soals, itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        soal = soals;
    }

    @Override
    public void onCekTugas(List<modelNilai> scores) {


    }

    @Override
    public void onRequestSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        myDialog.dismiss();

        Intent in = new Intent(this, TugasPage.class);
        in.putExtra("id_guru", id_guru);
        in.putExtra("nisn", nisn);
        in.putExtra("id_soal", id_soal);
        in.putExtra("nama_soal", nama_soal);
        in.putExtra("nama_guru", nama_guru);
        in.putExtra("nama_siswa", nama_siswa);
        in.putExtra("jumlah_soal", jumlah_soal);
        in.putExtra("durasi", durasi);
        in.putExtra("type_soal", type_soal);
        in.putExtra("score", score);
        startActivity(in);
        finish();


        setResult(RESULT_OK);
    }

    @Override
    public void onRequestError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        myDialog.dismiss();
//        myDialog.setContentView(R.layout.popup_putkunci);
    }
}
