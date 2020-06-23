package com.rentrust.id.edtrust.siswa.materi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.VideoView;

import com.rentrust.id.edtrust.R;

public class VideoPlayer extends AppCompatActivity {

    VideoView videoView;
    ImageView imageView;
    SeekBar seekBar;

    String vid_url;
    Boolean isPLay = false;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        init();
    }

    private void init() {
        videoView = findViewById(R.id.videoView);
        imageView = findViewById(R.id.toggleButton);
        seekBar = findViewById(R.id.seekBar);

        vid_url = getIntent().getStringExtra("video");

        videoView.setVideoPath(vid_url);

        handler = new Handler();
        videoView.start();
        isPLay = true;

        updateSeekBar();
    }

    private void updateSeekBar() {
        handler.postDelayed(updateTimeTask, 100);
        imageView.setImageResource(R.drawable.tg_pause);
    }

    public Runnable updateTimeTask = new Runnable() {
        @Override
        public void run() {
            seekBar.setProgress(videoView.getCurrentPosition());
            seekBar.setMax(videoView.getDuration());

            handler.postDelayed(this,100);

            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    handler.removeCallbacks(updateTimeTask);
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    handler.removeCallbacks(updateTimeTask);
                    videoView.seekTo(seekBar.getProgress());
                    updateSeekBar();
                }
            });
        }
    };

    public void toggle_method(View v) {
        if (isPLay) {
            videoView.pause();
            isPLay = false;
            imageView.setImageResource(R.drawable.tg_play);
        }
        else if (isPLay == false) {
            videoView.start();
            updateSeekBar();
            isPLay = true;
            imageView.setImageResource(R.drawable.tg_pause);
        }
    }

}
