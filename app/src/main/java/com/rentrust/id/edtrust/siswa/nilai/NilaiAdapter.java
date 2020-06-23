package com.rentrust.id.edtrust.siswa.nilai;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.rentrust.id.edtrust.R;
import com.rentrust.id.edtrust.model.modelNilai;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NilaiAdapter extends RecyclerView.Adapter<NilaiAdapter.RecyclerViewAdapter> {

    private Context context;
    private List<modelNilai> scores;
    private NilaiAdapter.ItemClickListener itemClickListener;


    public NilaiAdapter(
            Context context, List<modelNilai> scores,
            NilaiAdapter.ItemClickListener itemClickListener) {

        this.context = context;
        this.scores = scores;
        this.itemClickListener = itemClickListener;

    }

    @NonNull
    @Override
    public NilaiAdapter.RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_nilai,
                parent, false);
        return new NilaiAdapter.RecyclerViewAdapter(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull NilaiAdapter.RecyclerViewAdapter holder, int position) {
        modelNilai score = scores.get(position);

        holder.tv_nama_soal.setText(score.getNama_soal());
        holder.tv_nama_guru.setText("Nama guru : " + score.getNama_guru());
        holder.tv_jenis.setText("Jenis soal : " + score.getJenis_soal());
        holder.tv_type.setText("Type soal : " + score.getType_soal());


        String date1 = score.getTgl();
        SimpleDateFormat spf1 = new SimpleDateFormat("yyyy-MM-dd");
        Date newDate = null;
        try {
            newDate = spf1.parse(score.getTgl());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        spf1 = new SimpleDateFormat("dd MMM yyyy");
        date1 = spf1.format(newDate);
        holder.tv_date_time.setText(date1 +" "+ score.getJam());

        holder.tv_score.setText(String.valueOf(score.getNilai()));

        //holder.card_items.setCardBackgroundColor(Color.parseColor(retur.getColor()));
        //holder.card_item.setCardBackgroundColor(Color.parseColor(order.getColor()));
    }

    @Override
    public int getItemCount() {
        return scores.size();
    }


    class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_nama_soal, tv_nama_guru, tv_jenis, tv_type, tv_date_time, tv_score;
        CardView card_item, card_items;
        NilaiAdapter.ItemClickListener itemClickListener;

        RecyclerViewAdapter(View itemTable, NilaiAdapter.ItemClickListener itemClickListener) {
            super(itemTable);
            this.itemClickListener = itemClickListener;

            tv_nama_soal = itemTable.findViewById(R.id.namaSoal);
            tv_nama_guru = itemTable.findViewById(R.id.namaGuru);
            tv_type = itemTable.findViewById(R.id.typeSoal);
            tv_jenis = itemTable.findViewById(R.id.jenisSoal);
            tv_date_time = itemTable.findViewById(R.id.dateTime);
            tv_score = itemTable.findViewById(R.id.score);
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