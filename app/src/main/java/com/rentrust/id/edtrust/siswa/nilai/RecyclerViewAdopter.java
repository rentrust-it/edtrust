package com.rentrust.id.edtrust.siswa.nilai;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.rentrust.id.edtrust.R;
import com.rentrust.id.edtrust.model.modelNilai;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RecyclerViewAdopter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;
    private List<modelNilai> scores;

    public RecyclerViewAdopter(Context mContext, List<modelNilai> scores) {
        this.scores = scores;
        this.mContext = mContext;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_nama_soal, tv_nama_guru, tv_jenis, tv_type, tv_date_time, tv_score;
        CardView card_item, card_items;
        public MyViewHolder(View itemTable) {
            super(itemTable);
            tv_nama_soal = itemTable.findViewById(R.id.namaSoal);
            tv_nama_guru = itemTable.findViewById(R.id.namaGuru);
            tv_type = itemTable.findViewById(R.id.typeSoal);
            tv_jenis = itemTable.findViewById(R.id.jenisSoal);
            tv_date_time = itemTable.findViewById(R.id.dateTime);
            tv_score = itemTable.findViewById(R.id.score);
            card_items = itemTable.findViewById(R.id.cardItem);
            card_item = itemTable.findViewById(R.id.card_item);
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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch(viewType){
            case 1:
            {
                View v = inflater.inflate(R.layout.list_nilai, parent, false);
                viewHolder = new MyViewHolder(v);
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

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        modelNilai model = scores.get(holder.getAdapterPosition() - holder.getAdapterPosition() / 4);

        if ((position + 1) % 4 != 0) {
            MyViewHolder viewHolder = (MyViewHolder) holder;

            viewHolder.tv_nama_soal.setText(model.getNama_soal());
            viewHolder.tv_nama_guru.setText("Nama guru : " + model.getNama_guru());
            viewHolder.tv_jenis.setText("Jenis soal : " + model.getJenis_soal());
            viewHolder.tv_type.setText("Type soal : " + model.getType_soal());

            String date1 = model.getTgl();
            SimpleDateFormat spf1 = new SimpleDateFormat("yyyy-MM-dd");
            Date newDate = null;
            try {
                newDate = spf1.parse(model.getTgl());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            spf1 = new SimpleDateFormat("dd MMM yyyy");
            date1 = spf1.format(newDate);
            viewHolder.tv_date_time.setText(date1 +" "+ model.getJam());

            viewHolder.tv_score.setText(String.valueOf(model.getNilai()));
        }

    }

    @Override
    public int getItemViewType(int position)
    {
        return (position + 1) % 4 == 0 ? 2:1;
    }

    @Override
    public int getItemCount() {
        return scores.size()+ scores.size() /4;
    }
}