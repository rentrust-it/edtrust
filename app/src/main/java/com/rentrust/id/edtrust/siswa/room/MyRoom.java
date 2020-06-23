package com.rentrust.id.edtrust.siswa.room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.MobileAds;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rentrust.id.edtrust.R;
import com.rentrust.id.edtrust.session.SessionManager;
import com.rentrust.id.edtrust.model.modelRoomSiswa;

import java.util.HashMap;
import java.util.List;

public class MyRoom extends AppCompatActivity implements RoomView {

    SessionManager sessionManager;

    private RecyclerView.LayoutManager layoutManager;

    FloatingActionButton fab;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefresh;
    Dialog myDialog;

    RoomPresenter presenter;
    RoomAdapter adapter;
    RoomAdapter.RecyclerViewClickListener listener;
    List<modelRoomSiswa> room;

    String nisn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        MobileAds.initialize(this, "ca-app-pub-2267434843946816~5396162999");

        fab = findViewById(R.id.joinRoom);
        recyclerView = findViewById(R.id.activity);
        swipeRefresh = findViewById(R.id.swipe_refresh);

        myDialog = new Dialog(this);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        sessionManager = new SessionManager(this);

        HashMap<String, String> user = sessionManager.getMainSesi();
        nisn = user.get(sessionManager.NISN);

        presenter = new RoomPresenter(this);
        presenter.getMyRoom(nisn);

        swipeRefresh.setOnRefreshListener(
                () -> presenter.getMyRoom(nisn)
        );

        listener = new RoomAdapter.RecyclerViewClickListener() {
            @Override
            public void onRowClick(View view, final int position) {
                String nama_room = room.get(position).getNama_room();
            }

            @Override
            public void onLoveClick(View view, final int position) {

                final int my_room = room.get(position).getMy_room();
                final String fav = room.get(position).getStatus_room();
                final ImageView mFav = view.findViewById(R.id.fav);

                if (fav.equals("TIDAK AKTIF")){
                    mFav.setImageResource(R.drawable.ic_favorite_off);
                    presenter.changeFav(nisn, my_room);
                    adapter.notifyDataSetChanged();
                } else {
                    mFav.setImageResource(R.drawable.ic_favorite_on);
                    presenter.changeFav(nisn, my_room);
                    adapter.notifyDataSetChanged();
                }

            }
        };

        fab.setOnClickListener(v ->  {
            myDialog.setContentView(R.layout.popup_join_room);

            Button btn_join;
            EditText et_kode_room;
            TextView close;
            ProgressBar loading;

            btn_join = myDialog.findViewById(R.id.join_room);
            et_kode_room = myDialog.findViewById(R.id.kode_room);
            close = myDialog.findViewById(R.id.close);
            loading = myDialog.findViewById(R.id.loading);

            et_kode_room.setFilters(new InputFilter[] {new InputFilter.AllCaps()});

            btn_join.setOnClickListener(views -> {

                String kode_room = et_kode_room.getText().toString().trim();

                if (kode_room.isEmpty()) {
                    et_kode_room.setError("Kode room tidak boleh kosong");
                } else {
                    loading.setVisibility(View.VISIBLE);
                    btn_join.setVisibility(View.GONE);

                    // Hiding keyboard
                    View vo = myDialog.getCurrentFocus();
                    if (vo != null) {
                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(vo.getWindowToken(), 0);
                    }

                    presenter.joinRoom(nisn, kode_room);
                }

            });

            close.setOnClickListener(views -> myDialog.dismiss());
            myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            myDialog.show();
        });

    }

    @Override
    protected void onResume() {
        presenter.getMyRoom(nisn);

        swipeRefresh.setOnRefreshListener(
                () -> presenter.getMyRoom(nisn)
        );

        super.onResume();
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
    public void onGetMyRoom(List<modelRoomSiswa> rooms) {
        adapter = new RoomAdapter(rooms, this, listener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        room = rooms;
    }

    @Override
    public void onRequestSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        myDialog.dismiss();
        onResume();
        setResult(RESULT_OK);
    }

    @Override
    public void onRequestError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        myDialog.setContentView(R.layout.popup_join_room);
    }
}
