package com.rentrust.id.edtrust.siswa.history;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;

import com.rentrust.id.edtrust.R;
import com.rentrust.id.edtrust.model.modelHistory;
import com.rentrust.id.edtrust.session.SessionManager;

import java.util.HashMap;
import java.util.List;

public class History extends AppCompatActivity implements HistoryView {

    SessionManager sessionManager;

    private RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefresh;

    HistoryPresenter presenter;
    HistoryAdapter adapter;
    HistoryAdapter.ItemClickListener itemClickListener;
    List<modelHistory> history;

    String nisn, nama_siswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerView = findViewById(R.id.recyclerView);
        swipeRefresh = findViewById(R.id.swipe_refresh);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        sessionManager = new SessionManager(this);

        HashMap<String, String> user = sessionManager.getMainSesi();
        nama_siswa = user.get(sessionManager.NAMA);
        nisn = user.get(sessionManager.NISN);

        presenter = new HistoryPresenter(this);
        presenter.getHistory(nisn);

        swipeRefresh.setOnRefreshListener(
                () -> presenter.getHistory(nisn)
        );

        itemClickListener = ((view, position) -> { });
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
    public void onGetHistory(List<modelHistory> histories) {
        adapter = new HistoryAdapter(History.this, histories, itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        history = histories;
    }

    @Override
    public void onRequestSuccess(String message) {

    }

    @Override
    public void onRequestError(String message) {

    }
}
