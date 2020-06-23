package com.rentrust.id.edtrust.siswa.tugas.worker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.TextView;

import com.rentrust.id.edtrust.R;

public class TugasPage extends AppCompatActivity {

    TextView txt_nama_soal, txt_nama_guru, txt_nama_siswa, txt_nisn, txt_type_soal, txt_durasi, txt_jumlah_soal, txt_score;
    Button confirm;

    String nama_soal, nama_guru, nama_siswa, nisn, type_soal;
    int id_guru, id_soal, durasi, jumlah_soal, score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tugas_page);

        txt_nama_soal = findViewById(R.id.namaSoal);
        txt_nama_guru = findViewById(R.id.namaGuru);
        txt_nama_siswa = findViewById(R.id.namaSiswa);
        txt_durasi = findViewById(R.id.durasi);
        txt_type_soal = findViewById(R.id.typeSoal);
        txt_jumlah_soal = findViewById(R.id.jmlSoal);
        txt_score = findViewById(R.id.score);
        txt_nisn = findViewById(R.id.nisn);
        confirm = findViewById(R.id.confirm);

        Intent intent = getIntent();
        id_guru = intent.getIntExtra("id_guru", 0);
        id_soal = intent.getIntExtra("id_soal", 0);
        nama_soal = intent.getStringExtra("nama_soal");
        nama_guru = intent.getStringExtra("nama_guru");
        nama_siswa = intent.getStringExtra("nama_siswa");
        nisn = intent.getStringExtra("nisn");
        type_soal = intent.getStringExtra("type_soal");
        durasi = intent.getIntExtra("durasi", 0);
        jumlah_soal = intent.getIntExtra("jumlah_soal", 0);
        score = intent.getIntExtra("score", 0);

        setDataExtra();

        confirm.setOnClickListener(v -> {
            Intent next = new Intent(this, OnWork.class);
            next.putExtra("id_soal", id_soal);
            next.putExtra("id_guru", id_guru);
            next.putExtra("durasi", durasi);
            startActivity(next);
            finish();
        });

    }

    private void setDataExtra() {
        String.valueOf(id_soal);
        String.valueOf(id_guru);
        txt_nama_soal.setText(nama_soal);
        txt_nama_guru.setText("Nama guru : " +nama_guru);
        txt_nama_siswa.setText(nama_siswa);
        txt_nisn.setText(nisn);
        txt_type_soal.setText("Type soal : " + type_soal);
        txt_durasi.setText("Durasi soal : " + String.valueOf(durasi) + " Menit");
        txt_jumlah_soal.setText("Jumlah soal : " + String.valueOf(jumlah_soal));
        txt_score.setText("Score persoal : " +String.valueOf(score) + " Poin");
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

        AlertDialog alertbox = new AlertDialog.Builder(this)
                .setMessage("Apa anda ingin meninggalkan tugas ini?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {

                        finish();
                        //close();


                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                })
                .show();

    }
}
