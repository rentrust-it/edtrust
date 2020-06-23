package com.rentrust.id.edtrust.siswa.tugas;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.rentrust.id.edtrust.R;
import com.rentrust.id.edtrust.model.modelSoal;

import java.util.List;

public class TugasAdapter extends RecyclerView.Adapter<TugasAdapter.RecyclerViewAdapter> {

    private Context context;
    private List<modelSoal> soals;
    private TugasAdapter.ItemClickListener itemClickListener;

    public TugasAdapter(
            Context context, List<modelSoal> soals,
            TugasAdapter.ItemClickListener itemClickListener) {

        this.context = context;
        this.soals = soals;
        this.itemClickListener = itemClickListener;

    }

    @NonNull
    @Override
    public TugasAdapter.RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_soal,
                parent, false);
        return new TugasAdapter.RecyclerViewAdapter(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TugasAdapter.RecyclerViewAdapter holder, int position) {
        modelSoal soal = soals.get(position);

        holder.tv_nama_soal.setText(soal.getNama_soal());
        holder.tv_nama_guru.setText("Nama guru : " + soal.getNama_guru());
        holder.tv_jenis.setText("Jenis soal : " + soal.getJenis_soal());
        holder.tv_type.setText("Type soal : " + soal.getType_soal());
        holder.tv_durasi.setText(String.valueOf(soal.getDurasi()) +" Menit");

        //holder.card_items.setCardBackgroundColor(Color.parseColor(retur.getColor()));
        //holder.card_item.setCardBackgroundColor(Color.parseColor(order.getColor()));
    }

    @Override
    public int getItemCount() {
        return soals.size();
    }

    class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_nama_soal, tv_nama_guru, tv_jenis, tv_type, tv_durasi;
        CardView card_item, card_items;
        TugasAdapter.ItemClickListener itemClickListener;

        RecyclerViewAdapter(View itemTable, TugasAdapter.ItemClickListener itemClickListener) {
            super(itemTable);
            this.itemClickListener = itemClickListener;

            tv_nama_soal = itemTable.findViewById(R.id.namaSoal);
            tv_nama_guru = itemTable.findViewById(R.id.namaGuru);
            tv_type = itemTable.findViewById(R.id.typeSoal);
            tv_jenis = itemTable.findViewById(R.id.jenisSoal);
            tv_durasi = itemTable.findViewById(R.id.durasi);
            card_items = itemTable.findViewById(R.id.cardItem);
            card_item = itemTable.findViewById(R.id.card_item);

            card_item.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(v, getAdapterPosition());
        }
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}