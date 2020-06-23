package com.rentrust.id.edtrust.siswa.materi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.github.chrisbanes.photoview.PhotoView;
import com.rentrust.id.edtrust.R;
import com.rentrust.id.edtrust.model.modelMateri;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MateriImageAdapter extends RecyclerView.Adapter<MateriImageAdapter.RecyclerViewAdapter> {

    private Context context;
    private List<modelMateri> models;
    private MateriImageAdapter.ItemClickListener itemClickListener;

    public MateriImageAdapter(
            Context context, List<modelMateri> models,
            MateriImageAdapter.ItemClickListener itemClickListener) {

        this.context = context;
        this.models = models;
        this.itemClickListener = itemClickListener;

    }

    @NonNull
    @Override
    public MateriImageAdapter.RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_img,
                parent, false);
        return new MateriImageAdapter.RecyclerViewAdapter(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MateriImageAdapter.RecyclerViewAdapter holder, int position) {
        modelMateri itemTable = models.get(position);

        holder.tv_nama_materi.setText(itemTable.getNama_materi());
        holder.tv_keterangan.setText("Keterangan : " + itemTable.getKeterangan());
//        holder.tv_image.setImageResource(R.drawable.ic_placeholder_img);

//        Glide.with(context).load("http://i.imgur.com/DvpvklR.png").into(holder.tv_image);
//        Picasso.get().load("https://i.imgur.com/fUX7EIB.jpg").into(holder.tv_image);


        if (itemTable.getFile() != null) {
            Picasso.get().load(itemTable.getFile()).into(holder.tv_image);
        } else {
            holder.tv_image.setImageResource(R.drawable.ic_placeholder_img);
        }



        //holder.card_items.setCardBackgroundColor(Color.parseColor(retur.getColor()));
        //holder.card_item.setCardBackgroundColor(Color.parseColor(order.getColor()));
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_nama_materi, tv_keterangan, tv_times;
        PhotoView tv_image;
        CardView card_item;
        MateriImageAdapter.ItemClickListener itemClickListener;

        RecyclerViewAdapter(View itemTable, MateriImageAdapter.ItemClickListener itemClickListener) {
            super(itemTable);
            this.itemClickListener = itemClickListener;

            tv_nama_materi = itemTable.findViewById(R.id.nama);
            tv_keterangan = itemTable.findViewById(R.id.keterangan);
            tv_times = itemTable.findViewById(R.id.times);
            tv_image = itemTable.findViewById(R.id.image);
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
