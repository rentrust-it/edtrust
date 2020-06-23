package com.rentrust.id.edtrust.siswa.room;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.rentrust.id.edtrust.R;
import com.rentrust.id.edtrust.model.modelRoomSiswa;

import java.util.ArrayList;
import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    List<modelRoomSiswa> rooms, roomsFilter;
    private Context context;
    private RecyclerViewClickListener mListener;
    CustomFilter filter;

    public RoomAdapter(List<modelRoomSiswa> rooms, Context context, RecyclerViewClickListener listener) {
        this.rooms = rooms;
        this.roomsFilter = rooms;
        this.context = context;
        this.mListener = listener;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        RecyclerViewClickListener mListener;
        ImageView mFav;
        TextView mNama_guru, mNama_room, mJumlah_peserta;
        CardView mRowContainer;

        public MyViewHolder(View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            mNama_room = itemView.findViewById(R.id.namaRoom);
            mNama_guru = itemView.findViewById(R.id.namaGuru);
            mJumlah_peserta = itemView.findViewById(R.id.jumlahPeserta);
            mFav = itemView.findViewById(R.id.fav);
            mRowContainer = itemView.findViewById(R.id.card_item);

            mListener = listener;
            mRowContainer.setOnClickListener(this);
            mFav.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.card_item:
                    mListener.onRowClick(mRowContainer, getAdapterPosition());
                    break;
                case R.id.fav:
                    mListener.onLoveClick(mFav, getAdapterPosition());
                    break;
                default:
                    break;
            }
        }
    }

    public static class ViewHolderAdMob extends RecyclerView.ViewHolder {
        public AdView mAdView;
        public ViewHolderAdMob(View view) {
            super(view);
            mAdView = view.findViewById(R.id.adView);

            AdRequest adRequest = new AdRequest.Builder()
                    .addTestDevice("CEE26DC1A5BBF0E603B08A5460483046")
                    .build();
            mAdView.loadAd(adRequest);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch(viewType){
            case 1:
            {
                View v = inflater.inflate(R.layout.list_room, parent, false);
                viewHolder = new MyViewHolder(v, mListener);
                break;
            }
            case 2:
            default:
            {
                View v = inflater.inflate(R.layout.banner_ad_row, parent, false);
                viewHolder = new ViewHolderAdMob(v);
                break;
            }
        }
        return viewHolder;

    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        modelRoomSiswa model = rooms.get(holder.getAdapterPosition() - holder.getAdapterPosition() / 4);

        if ((position + 3) % 4 != 0) {
            Log.i("ceks", String.valueOf(position));
            MyViewHolder viewHolder = (MyViewHolder) holder;

            viewHolder.mNama_room.setText(model.getNama_room());
            viewHolder.mNama_guru.setText("Nama guru: " + model.getNama_guru());
            viewHolder.mJumlah_peserta.setText("Jumlah peserta: " + model.getJumlah_peserta());

            String fav = model.getStatus_room();

            if (fav.equals("AKTIF")) {
                viewHolder.mFav.setImageResource(R.drawable.ic_favorite_on);
            } else {
                viewHolder.mFav.setImageResource(R.drawable.ic_favorite_off);
            }

        }

    }

    @Override
    public int getItemViewType(int position)
    {
        return (position + 3) % 4 == 0 ? 2 : 1;
    }

    @Override
    public int getItemCount()
    {
        Log.i("Cek",String.valueOf(rooms.size()));
        return rooms.size()+ rooms.size() /4;
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
//            filter = new CustomFilter((ArrayList<modelRoomSiswa>) roomsFilter,this);

        }
        return filter;
    }

    public interface RecyclerViewClickListener {
        void onRowClick(View view, int position);
        void onLoveClick(View view, int position);
    }

}