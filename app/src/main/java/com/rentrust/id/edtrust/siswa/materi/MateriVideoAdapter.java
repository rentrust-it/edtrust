package com.rentrust.id.edtrust.siswa.materi;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.rentrust.id.edtrust.R;
import com.rentrust.id.edtrust.model.modelMateri;
import com.rentrust.id.edtrust.siswa.materi.ui.SectionsPagerAdapter;

import java.util.List;

public class MateriVideoAdapter extends RecyclerView.Adapter<MateriVideoAdapter.RecyclerViewAdapter> {

    private Context context;
    private List<modelMateri> models;
    private MateriVideoAdapter.ItemClickListener itemClickListener;

    public MateriVideoAdapter(
            Context context, List<modelMateri> models,
            MateriVideoAdapter.ItemClickListener itemClickListener) {

        this.context = context;
        this.models = models;
        this.itemClickListener = itemClickListener;

    }

    @NonNull
    @Override
    public MateriVideoAdapter.RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_vid,
                parent, false);
        return new MateriVideoAdapter.RecyclerViewAdapter(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MateriVideoAdapter.RecyclerViewAdapter holder, int position) {
        modelMateri itemTable = models.get(position);

        holder.tv_nama_materi.setText(itemTable.getNama_materi());
        holder.tv_keterangan.setText("Keterangan : " + itemTable.getKeterangan());


        try {
            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
            holder.tv_exo = ExoPlayerFactory.newSimpleInstance(context, trackSelector);
            Uri videouri = Uri.parse(itemTable.getFile());
            DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("exoplayer_video");
            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
            MediaSource mediaSource = new ExtractorMediaSource(
                    videouri, dataSourceFactory, extractorsFactory, null, null);
            holder.tv_exo_view.setPlayer(holder.tv_exo);
            holder.tv_exo.prepare(mediaSource);
            holder.tv_exo.setPlayWhenReady(false);
        } catch (Exception e) {
            Log.e("FragVID", "Exoplayer error" + e.toString());
        }

//        holder.tv_videoPLayer.startPlay(itemTable.getFile(), MxVideoPlayer.SCREEN_LAYOUT_NORMAL, itemTable.getNama_materi());

    }


    @Override
    public int getItemCount() {
        return models.size();
    }

    class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_nama_materi, tv_keterangan, tv_times;
        PlayerView tv_exo_view;
        SimpleExoPlayer tv_exo;
        CardView card_item;
        MateriVideoAdapter.ItemClickListener itemClickListener;

        RecyclerViewAdapter(View itemTable, MateriVideoAdapter.ItemClickListener itemClickListener) {
            super(itemTable);
            this.itemClickListener = itemClickListener;

            tv_nama_materi = itemTable.findViewById(R.id.nama);
            tv_keterangan = itemTable.findViewById(R.id.keterangan);
            tv_times = itemTable.findViewById(R.id.times);
            tv_exo_view = itemTable.findViewById(R.id.video_view);
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
