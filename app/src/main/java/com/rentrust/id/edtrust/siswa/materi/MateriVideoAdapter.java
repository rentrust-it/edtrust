package com.rentrust.id.edtrust.siswa.materi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.os.Build;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rentrust.id.edtrust.R;
import com.rentrust.id.edtrust.model.modelMateri;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

public class MateriVideoAdapter extends RecyclerView.Adapter<MateriVideoAdapter.RecyclerViewAdapter> {

    private Context context;
    private Activity activity;
    private List<modelMateri> models;
    private MateriVideoAdapter.ItemClickListener itemClickListener;

    public MateriVideoAdapter(
            Context context,
            Activity activity,
            List<modelMateri> models,
            MateriVideoAdapter.ItemClickListener itemClickListener) {

        this.context = context;
        this.activity = activity;
        this.models = models;
        this.itemClickListener = itemClickListener;

    }

    @NonNull
    @Override
    public MateriVideoAdapter.RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_vid,
                parent, false);

        return new RecyclerViewAdapter(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MateriVideoAdapter.RecyclerViewAdapter holder, int position) {
        modelMateri itemTable = models.get(position);

        holder.tv_nama_materi.setText(itemTable.getNama_materi());
        holder.tv_keterangan.setText("Keterangan : " + itemTable.getKeterangan());


        Picasso.get().load(itemTable.getThumb())
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .placeholder(R.drawable.no_thumbnail)
                .into(holder.tv_thumb);

//        Glide.with(context).load(itemTable.getThumb())
//                .skipMemoryCache(false)
//                .into(holder.tv_thumb);

        holder.r1_select.setAlpha(0);

//        holder.r1_select.setBackground(bitmapDrawable);

        holder.r1_select.setOnClickListener(v -> {
            Intent i = new Intent(context, VideoPlayer.class);
            i.putExtra("video", itemTable.getFile());
            activity.startActivity(i);
        });

        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(itemTable.getFile(), new HashMap<String, String>());
        String time = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
        long timeInMillisec = Long.parseLong(time);
        retriever.release();
        String duration=convertMillieToHMmSs(timeInMillisec); //use this duration



        holder.tv_times.setText(duration);

//        holder.tv_videoPLayer.startPlay(itemTable.getFile(), MxVideoPlayer.SCREEN_LAYOUT_NORMAL, itemTable.getNama_materi());

    }

    public static String convertMillieToHMmSs(long millie) {
        long seconds = (millie / 1000);
        long second = seconds % 60;
        long minute = (seconds / 60) % 60;
        long hour = (seconds / (60 * 60)) % 24;

        String result = "";
        if (hour > 0) {
            return String.format("%02d:%02d:%02d", hour, minute, second);
        }
        else {
            return String.format("%02d:%02d" , minute, second);
        }

    }


    @Override
    public int getItemCount() {
        return models.size();
    }

    class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_nama_materi, tv_keterangan, tv_times;
        ImageView tv_thumb;
        RelativeLayout r1_select;
        CardView card_item;
        MateriVideoAdapter.ItemClickListener itemClickListener;

        RecyclerViewAdapter(View itemTable, MateriVideoAdapter.ItemClickListener itemClickListener) {
            super(itemTable);
            this.itemClickListener = itemClickListener;

            tv_nama_materi = itemTable.findViewById(R.id.nama);
            tv_keterangan = itemTable.findViewById(R.id.keterangan);
            tv_times = itemTable.findViewById(R.id.times);
            tv_thumb = itemTable.findViewById(R.id.thumb);

            r1_select = itemTable.findViewById(R.id.r1_select);

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
