package com.rentrust.id.edtrust.siswa.nilai;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;

import com.google.android.gms.ads.MobileAds;
import com.rentrust.id.edtrust.R;
import com.rentrust.id.edtrust.model.modelNilai;
import com.rentrust.id.edtrust.session.SessionManager;

import java.util.HashMap;
import java.util.List;

public class Nilai extends AppCompatActivity implements NilaiView {

    SessionManager sessionManager;

    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefresh;

    NilaiPresenter presenter;
    RecyclerViewAdopter adapter;
    List<modelNilai> score;

    String nisn, nama_siswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nilai);

        MobileAds.initialize(this, "ca-app-pub-2267434843946816~5396162999");

        recyclerView = findViewById(R.id.recyclerView);
        swipeRefresh = findViewById(R.id.swipe_refresh);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        sessionManager = new SessionManager(this);

        HashMap<String, String> user = sessionManager.getMainSesi();
        nama_siswa = user.get(sessionManager.NAMA);
        nisn = user.get(sessionManager.NISN);

        presenter = new NilaiPresenter(this);
        presenter.getNilai(nisn);

        swipeRefresh.setOnRefreshListener(
                () -> presenter.getNilai(nisn)
        );

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
    public void onGetNilai(List<modelNilai> scores) {
        adapter = new RecyclerViewAdopter(Nilai.this, scores);
        adapter.notifyDataSetChanged();
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        score = scores;


    }

    @Override
    public void onRequestSuccess(String message) {

    }

    @Override
    public void onRequestError(String message) {

    }


}
