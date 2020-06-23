package com.rentrust.id.edtrust.siswa.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.rentrust.id.edtrust.R;
import com.rentrust.id.edtrust.model.modelSiswa;
import com.rentrust.id.edtrust.session.SessionManager;

import java.util.HashMap;
import java.util.List;

public class ChangePassword extends AppCompatActivity implements ProfileView {

    EditText curr_pass, new_pass;
    ProgressBar loading;

    SessionManager sessionManager;

    ProfilePresenter presenter;

    Menu actionMenu;

    String nisn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        curr_pass = findViewById(R.id.curr_pass);
        new_pass = findViewById(R.id.new_pass);
        loading = findViewById(R.id.loading);


        sessionManager = new SessionManager(this);
        sessionManager.checkSiswa();

        HashMap<String, String> user = sessionManager.getMainSesi();
        nisn = user.get(sessionManager.NISN);

        presenter = new ProfilePresenter(this);


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

    }

    @Override
    public void onRequestSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);

        finish();
    }

    @Override
    public void onRequestSuccessUpload(String message) {

    }

    @Override
    public void onRequestError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.change_pass, menu);
        actionMenu = menu;

        menu.findItem(R.id.action_change_password).setVisible(true);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_change_password:

                String cpass = curr_pass.getText().toString().trim();
                String npass = new_pass.getText().toString().trim();

                if (cpass.isEmpty()) {
                    curr_pass.setError("Field ini tidak boleh kosong");
                } else if (npass.isEmpty()) {
                    new_pass.setError("Field ini tidak boleh kosong");
                }else {
                    presenter.changePassword(nisn, cpass, npass);
                }

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
