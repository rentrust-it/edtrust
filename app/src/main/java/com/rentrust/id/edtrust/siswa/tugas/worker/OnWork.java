package com.rentrust.id.edtrust.siswa.tugas.worker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.rentrust.id.edtrust.R;
import com.rentrust.id.edtrust.model.modelPGanda;
import com.rentrust.id.edtrust.model.modelSoal;

import org.json.JSONObject;

import java.util.List;
import java.util.Locale;

public class OnWork extends AppCompatActivity implements WorkerView {

    TextView timer, nomor, pertanyaan, hurry_up;
    RadioGroup radioGroup;
    RadioButton a, b, c, d, e;
    RecyclerView recyclerView;
    CardView cardFinish;
    ScrollView no_shimmer;
    ShimmerFrameLayout shimmer;

    private RecyclerView.LayoutManager layoutManager;

    WorkerPresenter presenter;
    WorkerAdapter adapter;
    WorkerAdapter.ItemClickListener itemClickListener;
    List<modelPGanda> ganda;

    int id_soal, id_guru, durasi, no_index, nilai = 0;
    String jawaban;

    private long timeLeftInMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_work);

        timer = findViewById(R.id.timer);
        hurry_up = findViewById(R.id.txt_hurry);
        nomor = findViewById(R.id.nomor);
        pertanyaan = findViewById(R.id.soal);
        a = findViewById(R.id.a);
        b = findViewById(R.id.b);
        c = findViewById(R.id.c);
        d = findViewById(R.id.d);
        e = findViewById(R.id.e);
        radioGroup = findViewById(R.id.soalGroup);

        recyclerView = findViewById(R.id.recycler);
        cardFinish = findViewById(R.id.cardFinish);
        no_shimmer = findViewById(R.id.no_shimmer);
        shimmer = findViewById(R.id.shimmer);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        Intent intent = getIntent();
        id_soal = intent.getIntExtra("id_soal", 0);
        id_guru = intent.getIntExtra("id_guru", 0);
        durasi = intent.getIntExtra("durasi", 0);

        int millis = durasi*60000;

        new CountDownTimer(millis, 1000) {

            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;

                if (millisUntilFinished < 600000) {
                    hurry_up.setVisibility(View.VISIBLE);
                } else {
                    hurry_up.setVisibility(View.GONE);
                }

                updateCountdown();
            }

            public void onFinish() {
                timer.setText("done!");
            }
        }.start();

        presenter = new WorkerPresenter(this);
        presenter.getSoal(id_soal);

        itemClickListener = ((view, position) -> {

            nomor.setText("No. " + ganda.get(position).getNomor());
            pertanyaan.setText(ganda.get(position).getPertanyaan());
            a.setText("A. " + ganda.get(position).getA());
            b.setText("B. " + ganda.get(position).getB());
            c.setText("C. " + ganda.get(position).getC());
            d.setText("D. " + ganda.get(position).getD());
            e.setText("E. " + ganda.get(position).getE());

            jawaban = ganda.get(position).getJawaban();
            no_index = position;

            a.setChecked(false);
            b.setChecked(false);
            c.setChecked(false);
            d.setChecked(false);
            e.setChecked(false);

            radioGroup.clearCheck();

            if (jawaban.equals("")) {
                a.setChecked(false);
                b.setChecked(false);
                c.setChecked(false);
                d.setChecked(false);
                e.setChecked(false);
            } else if(jawaban.equals("A")) {
                a.setChecked(true);
                b.setChecked(false);
                c.setChecked(false);
                d.setChecked(false);
                e.setChecked(false);
            } else if (jawaban.equals("B")) {
                a.setChecked(false);
                b.setChecked(true);
                c.setChecked(false);
                d.setChecked(false);
                e.setChecked(false);
            } else if (jawaban.equals("C")) {
                a.setChecked(false);
                b.setChecked(false);
                c.setChecked(true);
                d.setChecked(false);
                e.setChecked(false);
            } else if (jawaban.equals("D")) {
                a.setChecked(false);
                b.setChecked(false);
                c.setChecked(false);
                d.setChecked(true);
                e.setChecked(false);
            } else if (jawaban.equals("E")) {
                a.setChecked(false);
                b.setChecked(false);
                c.setChecked(false);
                d.setChecked(false);
                e.setChecked(true);
            }


//            Toast.makeText(this, ganda.get(position).getJawaban(), Toast.LENGTH_SHORT).show();

//            for(int i = 0; i < ganda.size(); i++) {
//                Log.i("Cek", ganda.get(i).getNomor()+" - " +ganda.get(i).getJawaban());
//            }

        });

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
                // TODO Auto-generated method stub
                if(a.isChecked())
                {
//                    Toast.makeText(this, "A", Toast.LENGTH_SHORT).show();
                    ganda.get(no_index).setJawaban("A");
                    adapter.notifyDataSetChanged();
                }
                else if(b.isChecked())
                {
//                    Toast.makeText(this, "B", Toast.LENGTH_SHORT).show();
                    ganda.get(no_index).setJawaban("B");
                    adapter.notifyDataSetChanged();
                }
                else if(c.isChecked())
                {
//                    Toast.makeText(this, "C", Toast.LENGTH_SHORT).show();
                    ganda.get(no_index).setJawaban("C");
                    adapter.notifyDataSetChanged();
                }
                else if(d.isChecked())
                {
//                    Toast.makeText(this, "D", Toast.LENGTH_SHORT).show();
                    ganda.get(no_index).setJawaban("D");
                    adapter.notifyDataSetChanged();
                }
                else if(e.isChecked())
                {
//                    Toast.makeText(this, "E", Toast.LENGTH_SHORT).show();
                    ganda.get(no_index).setJawaban("E");
                    adapter.notifyDataSetChanged();
                }
        });

        cardFinish.setOnClickListener(v -> {
            AlertDialog alertbox = new AlertDialog.Builder(this)
                    .setMessage("Apakah anda ingin mengakhiri tugas ini ?")
                    .setPositiveButton("Ya", (arg0, arg1) -> {

                        for(int i = 0; i < ganda.size(); i++) {
                            String nKunci = ganda.get(i).getKunci();
                            String nJawaban = ganda.get(i).getJawaban();
                            int score = ganda.get(i).getScore();

                            if (nKunci.equals(nJawaban)) {
                                nilai = nilai + score;
                            } else {
                                nilai = nilai + 0;
                            }

                        }

//                        Log.i("Cek", String.valueOf(nilai));

                        Intent out = new Intent(this, TugasSelesai.class);
                        out.putExtra("nilai", nilai);
                        out.putExtra("id_soal", id_soal);
                        out.putExtra("id_guru", id_guru);
                        startActivity(out);
                        finish();


                    })
                    .setNegativeButton("Tidak", (arg0, arg1) -> {

//                    do something here
                    })
                    .show();
        });

    }

    private void updateCountdown() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeLeftFormatted =  String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        timer.setText(timeLeftFormatted);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitByBackKey();

            //moveTaskToBack(false);

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void exitByBackKey() {

        // do something when the button is clicked
        // do something when the button is clicked
        AlertDialog alertbox = new AlertDialog.Builder(this)
                .setMessage("Do you want to exit application?")
                .setPositiveButton("Yes", (arg0, arg1) -> {

                    finish();
                    //close();


                })
                .setNegativeButton("No", (arg0, arg1) -> {

//                    do something here
                })
                .show();

    }

    @Override
    public void showLoading() {
        shimmer.setVisibility(View.VISIBLE);
        no_shimmer.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        shimmer.setVisibility(View.GONE);
        no_shimmer.setVisibility(View.VISIBLE);
    }

    @Override
    public void onGetSoal(List<modelPGanda> gandas) {
        adapter = new WorkerAdapter(OnWork.this, gandas, itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        ganda = gandas;

        nomor.setText("No. " + gandas.get(0).getNomor());
        pertanyaan.setText(gandas.get(0).getPertanyaan());

        a.setText("A. " + gandas.get(0).getA());
        b.setText("B. " + gandas.get(0).getB());
        c.setText("C. " + gandas.get(0).getC());
        d.setText("D. " + gandas.get(0).getD());
        e.setText("E. " + gandas.get(0).getE());

    }

    @Override
    public void onRequestSuccess(String message) {

    }

    @Override
    public void onRequestError(String message) {

    }
}
