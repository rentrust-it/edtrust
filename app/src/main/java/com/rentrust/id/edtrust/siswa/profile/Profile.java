package com.rentrust.id.edtrust.siswa.profile;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.google.android.material.textfield.TextInputLayout;
import com.rentrust.id.edtrust.R;
import com.rentrust.id.edtrust.model.modelSiswa;
import com.rentrust.id.edtrust.session.SessionManager;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends AppCompatActivity implements ProfileView {

    private static final int IMG_REQUEST = 777;
    private Bitmap bitmap;

    CircleImageView img_photo;
    ImageView edit_nama, edit_password, save_nama;
    TextView txt_nama, txt_nisn, txt_username, upload, txt_password;
    TextInputLayout layout_nama;
    EditText et_nama, et_password;
    Button btn_logout;
    ProgressBar loading;

    SessionManager sessionManager;

    ProfilePresenter presenter;

    String nama, nisn, username, newName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

//        ImageView
        img_photo = findViewById(R.id.photo);
        edit_nama = findViewById(R.id.editNama);
        save_nama = findViewById(R.id.saveNama);
        edit_password = findViewById(R.id.editPass);

//        TextView
        upload = findViewById(R.id.upload);
        txt_nama = findViewById(R.id.nama);
        txt_nisn = findViewById(R.id.nisn);
        txt_username = findViewById(R.id.username);
        txt_password = findViewById(R.id.pass);

//        LayoutInput
        layout_nama = findViewById(R.id.layoutnama);

//        EditText
        et_nama = findViewById(R.id.et_nama);

        btn_logout = findViewById(R.id.logout);
        loading = findViewById(R.id.loading);


        sessionManager = new SessionManager(this);
        sessionManager.checkSiswa();

        HashMap<String, String> user = sessionManager.getMainSesi();
        nama = user.get(sessionManager.NAMA);
        nisn = user.get(sessionManager.NISN);
        username = user.get(sessionManager.USERNAME);

        presenter = new ProfilePresenter(this);
        presenter.getPhotoProfile(nisn);

        txt_nama.setText("Nama : " + nama);
        txt_nisn.setText("NISN : " + nisn);
        txt_username.setText("Username : " + username);
        et_nama.setText(nama);

        img_photo.setOnClickListener(v -> {
            chooseFile();
        });

        upload.setOnClickListener(v -> {
            chooseFile();
        });

        edit_nama.setOnClickListener(v -> {
            layout_nama.setVisibility(View.VISIBLE);
            save_nama.setVisibility(View.VISIBLE);

            txt_nama.setVisibility(View.GONE);
            edit_nama.setVisibility(View.GONE);
        });

        edit_password.setOnClickListener(v -> {
            Intent cp = new Intent(Profile.this, ChangePassword.class);
            startActivity(cp);
        });

        save_nama.setOnClickListener(v -> {
            newName = et_nama.getText().toString().trim();

            if (newName.isEmpty()) {
                et_nama.setError("Nama tidak boleh kosong");
            } else {
                presenter.changeName(nisn, newName);
            }
        });

        btn_logout.setOnClickListener(v -> {
            sessionManager.logoutSiswa();
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        sessionManager.checkSiswa();
        HashMap<String, String> user = sessionManager.getMainSesi();
        nama = user.get(sessionManager.NAMA);
        txt_nama.setText("Nama : " + nama);

    }

    private void chooseFile() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, IMG_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == IMG_REQUEST && data!=null) {

            Uri path = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                img_photo.setImageBitmap(bitmap);
                uploadImage();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void uploadImage() {

        String photo = imageToString();
        presenter.changePhoto(nisn, photo);

    }

    private String imageToString() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte,Base64.DEFAULT);
    }

    @Override
    public void showLoading() {
        loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loading.setVisibility(View.GONE);
    }

    @Override
    public void onGetProfile(List<modelSiswa> siswas) {
        String data_image = siswas.get(0).getFoto();
        Picasso.get().load("https://rentrust.tech/edtrust/siswa/" + data_image)
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .placeholder(R.drawable.ic_person)
                .into(img_photo);
    }

    @Override
    public void onRequestSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        onResume();
        setResult(RESULT_OK);

        layout_nama.setVisibility(View.GONE);
        save_nama.setVisibility(View.GONE);

        txt_nama.setVisibility(View.VISIBLE);
        edit_nama.setVisibility(View.VISIBLE);

        sessionManager.createSession(newName, username, nisn);

        HashMap<String, String> user = sessionManager.getMainSesi();
        user.put("NAMA", newName);

        onResume();



    }

    @Override
    public void onRequestSuccessUpload(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);

        presenter.getPhotoProfile(nisn);
    }

    @Override
    public void onRequestError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    //    @Override
//    public void onActivityResult(int resultCode, int requestCode, Intent data) {
//        super.onActivityResult(resultCode, requestCode, data);
//        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
//            imageUri = data.getData();
//            img_photo.setImageURI(imageUri);
//        }
//    }
}
