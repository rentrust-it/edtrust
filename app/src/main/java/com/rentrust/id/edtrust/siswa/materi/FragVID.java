package com.rentrust.id.edtrust.siswa.materi;


import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.rentrust.id.edtrust.R;
import com.rentrust.id.edtrust.model.modelMateri;
import com.rentrust.id.edtrust.session.SessionManager;

import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;

public class FragVID extends Fragment implements MateriView{
    private static final String ARG_NAME = "argName";
    private static final String ARG_ID = "argId";

    private View v;

    private SessionManager sessionManager;

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefresh;
    private AdView ad_banner;

    private MateriPresenter presenter;
    private MateriVideoAdapter adapter;
    private MateriVideoAdapter.ItemClickListener itemClickListener;
    private List<modelMateri> model;

    private String nisn, nama_siswa, nama_room;
    private int id_guru;

    public static FragVID newInstance(String nama_room, int id_guru) {
        FragVID fragVID = new FragVID();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, nama_room);
        args.putInt(ARG_ID, id_guru);
        fragVID.setArguments(args);
        return fragVID;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.frag_vid, container, false);

        ButterKnife.bind(this, v);

        recyclerView = v.findViewById(R.id.recyclerView);
        swipeRefresh = v.findViewById(R.id.swipe_refresh);
        ad_banner = v.findViewById(R.id.adView);

        MobileAds.initialize(getContext(), "ca-app-pub-2267434843946816~5396162999");

        AdRequest adRequest = new AdRequest.Builder().addTestDevice("CEE26DC1A5BBF0E603B08A5460483046").build();
        ad_banner.loadAd(adRequest);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setHasFixedSize(true);

        sessionManager = new SessionManager(getContext());

        if (getArguments() != null) {
            nama_room = getArguments().getString(ARG_NAME);
            id_guru = getArguments().getInt(ARG_ID);

        }

        presenter = new MateriPresenter(this);
        presenter.getVideo(nama_room, id_guru);

        swipeRefresh.setOnRefreshListener(
                () -> presenter.getVideo(nama_room, id_guru)
        );

        itemClickListener =((view, position) -> {
            Toast.makeText(getContext(), "Testing", Toast.LENGTH_SHORT).show();
        });

        HashMap<String, String> user = sessionManager.getMainSesi();
        nama_siswa = user.get(sessionManager.NAMA);
        nisn = user.get(sessionManager.NISN);

        return v;

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
    public void onGetFile(List<modelMateri> models) {
        adapter = new MateriVideoAdapter(getContext(), getActivity(), models, itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


        model = models;
    }

    @Override
    public void onRequestSuccess(String message) {

    }

    @Override
    public void onRequestError(String message) {

    }
}
