package com.rentrust.id.edtrust.siswa.materi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.rentrust.id.edtrust.R;
import com.rentrust.id.edtrust.model.modelMateri;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MateriFileAdapter extends RecyclerView.Adapter<MateriFileAdapter.RecyclerViewAdapter> {

    private Context context;
    private List<modelMateri> models;
    private MateriFileAdapter.ItemClickListener itemClickListener;

    public MateriFileAdapter(
            Context context, List<modelMateri> models,
            MateriFileAdapter.ItemClickListener itemClickListener) {

        this.context = context;
        this.models = models;
        this.itemClickListener = itemClickListener;

    }

    @NonNull
    @Override
    public MateriFileAdapter.RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_file,
                parent, false);
        return new MateriFileAdapter.RecyclerViewAdapter(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MateriFileAdapter.RecyclerViewAdapter holder, int position) {
        modelMateri itemTable = models.get(position);

        holder.tv_nama_materi.setText(itemTable.getNama_materi());
        holder.tv_nama_guru.setText("Nama guru : " + itemTable.getNama_guru());
        holder.tv_keterangan.setText("Keterangan : " + itemTable.getKeterangan());

            String date1 = itemTable.getTgl_materi();
            SimpleDateFormat spf1 = new SimpleDateFormat("yyyy-MM-dd");
            Date newDate = null;
            try {
                newDate = spf1.parse(itemTable.getTgl_materi());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            spf1 = new SimpleDateFormat("dd MMM yyyy");
            date1 = spf1.format(newDate);
            holder.tv_date_time.setText(date1 +" "+ itemTable.getJam_materi());


        //holder.card_items.setCardBackgroundColor(Color.parseColor(retur.getColor()));
        //holder.card_item.setCardBackgroundColor(Color.parseColor(order.getColor()));
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_nama_materi, tv_nama_guru, tv_keterangan, tv_date_time;
        CardView card_item, card_items;
        MateriFileAdapter.ItemClickListener itemClickListener;

        RecyclerViewAdapter(View itemTable, MateriFileAdapter.ItemClickListener itemClickListener) {
            super(itemTable);
            this.itemClickListener = itemClickListener;

            tv_nama_materi = itemTable.findViewById(R.id.namaMateri);
            tv_nama_guru = itemTable.findViewById(R.id.namaGuru);
            tv_keterangan = itemTable.findViewById(R.id.keterangan);
            tv_date_time = itemTable.findViewById(R.id.dateTime);
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
