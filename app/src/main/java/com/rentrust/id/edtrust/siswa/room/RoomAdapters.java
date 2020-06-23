package com.rentrust.id.edtrust.siswa.room;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.rentrust.id.edtrust.R;
import com.rentrust.id.edtrust.model.modelRoomSiswa;

import java.util.ArrayList;
import java.util.List;

public class RoomAdapters extends RecyclerView.Adapter<RoomAdapters.MyViewHolder> implements Filterable {

    List<modelRoomSiswa> rooms, roomsFilter;
    private Context context;
    private RecyclerViewClickListener mListener;
    CustomFilter filter;

    public RoomAdapters(List<modelRoomSiswa> rooms, Context context, RecyclerViewClickListener listener) {
        this.rooms = rooms;
        this.roomsFilter = rooms;
        this.context = context;
        this.mListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_room, parent, false);
        return new MyViewHolder(view, mListener);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        holder.mNama_room.setText(rooms.get(position).getNama_room());
        holder.mNama_guru.setText("Nama guru: "+rooms.get(position).getNama_guru());
        holder.mJumlah_peserta.setText("Jumlah peserta: "+rooms.get(position).getJumlah_peserta());

        String fav = rooms.get(position).getStatus_room();

        if (fav.equals("AKTIF")){
            holder.mFav.setImageResource(R.drawable.ic_favorite_on);
        } else {
            holder.mFav.setImageResource(R.drawable.ic_favorite_off);
        }

    }

    @Override
    public int getItemCount() {
        return rooms.size();
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
//            filter = new CustomFilter((ArrayList<modelRoomSiswa>) roomsFilter,this);

        }
        return filter;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private RecyclerViewClickListener mListener;
        private ImageView mFav;
        private TextView mNama_guru, mNama_room, mJumlah_peserta;
        private CardView mRowContainer;

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

    public interface RecyclerViewClickListener {
        void onRowClick(View view, int position);
        void onLoveClick(View view, int position);
    }

}