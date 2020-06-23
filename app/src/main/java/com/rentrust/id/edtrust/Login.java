package com.rentrust.id.edtrust;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.rentrust.id.edtrust.model.modelSiswa;
import com.rentrust.id.edtrust.session.SessionManager;
import com.rentrust.id.edtrust.siswa.Siswa;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Login extends AppCompatActivity implements RegView {

    private EditText username, password;
    private Button btn_login;
    private TextView btn_register;

    AdView ad_banner;
    RadioGroup radioGroup;

    private InterstitialAd interstitialAd;

    //private ImageView logo;
    private ProgressBar loading;
    private static String URL_LOGIN = "https://rentrust.tech/edtrust/login-siswa";
    SessionManager sessionManager;

    Dialog myDialog;
    private String et_jenis_kelamin, et_tgl;
    Calendar myCalendar = Calendar.getInstance();

    RegPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        MobileAds.initialize(this, getResources().getString(R.string.adApps));

        myDialog = new Dialog(this);
        radioGroup = myDialog.findViewById(R.id.radioGroup);

        sessionManager = new SessionManager(this);
        //sessionManager.checkLogin();

        loading = findViewById(R.id.loading);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.register);
        ad_banner = findViewById(R.id.adBanner);
        //logo = findViewById(R.id.logo);

        AdRequest adRequest = new AdRequest.Builder().addTestDevice("CEE26DC1A5BBF0E603B08A5460483046").build();
        ad_banner.loadAd(adRequest);

        //AdMob
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-2267434843946816/8643220970");
        interstitialAd.loadAd(new AdRequest.Builder().addTestDevice("CEE26DC1A5BBF0E603B08A5460483046").build());

        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {

                AdRequest adRequest = new AdRequest.Builder().addTestDevice("ca-app-pub-2267434843946816/8643220970").build();
                interstitialAd.loadAd(adRequest);
            }
        });

        btn_login.setOnClickListener(v -> {
            String mUser = username.getText().toString().trim();
            String mPass = password.getText().toString().trim();

            if (!mUser.isEmpty() || !mPass.isEmpty()){
                Login(mUser, mPass);
            } else {
                username.setError("Please insert username");
                password.setError("Please insert password");
            }
        });

        btn_register.setOnClickListener(v -> {
            myDialog.setContentView(R.layout.popup_register);

            Button btn_registers;
            EditText et_username, et_password, et_nama, et_nisn, et_tgl_lahir;
            TextView close;
            ProgressBar loadingReg;
            RadioButton radPria, radWanita;

            btn_registers = myDialog.findViewById(R.id.register);
            et_username = myDialog.findViewById(R.id.username);
            et_password = myDialog.findViewById(R.id.password);
            et_nama = myDialog.findViewById(R.id.nama);
            et_nisn = myDialog.findViewById(R.id.nisn);
            et_tgl_lahir = myDialog.findViewById(R.id.tgl_lahir);

            close = myDialog.findViewById(R.id.close);
            loadingReg = myDialog.findViewById(R.id.loadingReg);

//            selected = radioGroup.getCheckedRadioButtonId();
//            radioButton = myDialog.findViewById(selected);

            radPria = myDialog.findViewById(R.id.radPria);
            radWanita = myDialog.findViewById(R.id.radWanita);

            radPria.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    et_jenis_kelamin = "LAKI-LAKI";
                    radPria.setTextColor(getResources().getColor(R.color.colorWhite));
                } else {
                    radPria.setTextColor(getResources().getColor(R.color.colorBlack));
                }
            });

            radWanita.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    et_jenis_kelamin = "PEREMPUAN";
                    radWanita.setTextColor(getResources().getColor(R.color.colorWhite));
                } else {
                    radWanita.setTextColor(getResources().getColor(R.color.colorBlack));
                }
            });

            et_tgl_lahir.setFocusableInTouchMode(false);
            et_tgl_lahir.setFocusable(false);

            DatePickerDialog.OnDateSetListener date = (view, year, monthOfYear, dayOfMonth) -> {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "yyyy-MM-dd"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                et_tgl_lahir.setText(sdf.format(myCalendar.getTime()));
            };

            et_tgl_lahir.setOnClickListener(v1 -> new DatePickerDialog(Login.this, date,
                    myCalendar.get(Calendar.YEAR),
                    myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show());


            btn_registers.setOnClickListener(view -> {

                String username = et_username.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                String nama = et_nama.getText().toString().trim();
                String nisn = et_nisn.getText().toString().trim();
                String tgl_lahir = et_tgl_lahir.getText().toString().trim();
                String jk = et_jenis_kelamin;

                if (username.isEmpty()) {
                    et_username.setError("Username tidak boleh kosong");
                } else if (password.isEmpty()) {
                    et_password.setError("Password tidak boleh kosong");
                } else if (nisn.isEmpty()) {
                    et_nisn.setError("Email tidak boleh kosong");
                } else if (nama.isEmpty()) {
                    et_nama.setError("Nama tidak boleh kosong");
                } else if (tgl_lahir.isEmpty()) {
                    et_tgl_lahir.setError("Tanggal lahir tidak boleh kosong");
                } else {

                    loadingReg.setVisibility(View.VISIBLE);
                    btn_registers.setVisibility(View.GONE);

                    // Hiding keyboard
                    View vo = myDialog.getCurrentFocus();
                    if (vo != null) {
                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(vo.getWindowToken(), 0);
                    }

                    presenter = new RegPresenter(this);
                    presenter.registerSiswa(nisn, username, password, nama, jk, tgl_lahir);

//                    Log.i("Test", nisn +" "+ username +" "+ password +" "+ nama +" "+ jk +" "+ tgl_lahir);
                }

            });

            close.setOnClickListener(view -> myDialog.dismiss());
            myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            myDialog.show();
        });

    }

    public void onRadioButtonClicked(View views) {
        boolean checked = ((RadioButton) views).isChecked();

        switch(views.getId()) {

            case R.id.radPria:
                if (checked)
                    et_jenis_kelamin = "LAKI-LAKI";
                break;
            case R.id.radWanita:
                if (checked)
                    et_jenis_kelamin = "PEREMPUAN";
                break;
        }
    }

    private void Login(final String username, final String password) {

        loading.setVisibility(View.VISIBLE);
        btn_login.setVisibility(View.GONE);
        btn_register.setVisibility(View.GONE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String success = jsonObject.getString("success");
                        JSONArray jsonArray = jsonObject.getJSONArray("login");

                        if(success.equals("1")) {

                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject object = jsonArray.getJSONObject(i);

                                if(interstitialAd.isLoaded()) {

                                    String usernames = object.getString("username").trim();
                                    String nama = object.getString("nama").trim();
                                    String nisn = object.getString("nisn").trim();

                                    sessionManager.createSession(nama, usernames, nisn);

                                    Intent intent = new Intent(getApplicationContext(), Siswa.class);
                                    startActivity(intent);
                                    finish();

                                    loading.setVisibility(View.GONE);
                                    interstitialAd.show();
                                } else {

                                    String usernames = object.getString("username").trim();
                                    String nama = object.getString("nama").trim();
                                    String nisn = object.getString("nisn").trim();

                                    sessionManager.createSession(nama, usernames, nisn);

                                    Intent intent = new Intent(getApplicationContext(), Siswa.class);
                                    startActivity(intent);
                                    finish();

                                    loading.setVisibility(View.GONE);

                                }

                            }

                        } else {

                            loading.setVisibility(View.GONE);
                            btn_login.setVisibility(View.VISIBLE);
                            btn_register.setVisibility(View.VISIBLE);
                            Toast.makeText(Login.this, "Wrong username or password \nTry again", Toast.LENGTH_SHORT).show();

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        loading.setVisibility(View.GONE);
                        btn_login.setVisibility(View.VISIBLE);
                        btn_register.setVisibility(View.VISIBLE);
                        Toast.makeText(Login.this, "Something wrong \nTry again" +e.toString(), Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    loading.setVisibility(View.GONE);
                    btn_login.setVisibility(View.VISIBLE);
                    btn_register.setVisibility(View.VISIBLE);
                    Toast.makeText(Login.this, "Error" +error.toString(), Toast.LENGTH_SHORT).show();
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

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
    public void onGetSiswa(List<modelSiswa> siswas) {

    }

    @Override
    public void onRequestSuccess(String message) {
        myDialog.dismiss();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);

    }

    @Override
    public void onRequestError(String message) {
        Button btn_register;
        ProgressBar loadingReg;

        btn_register = myDialog.findViewById(R.id.register);
        loadingReg = myDialog.findViewById(R.id.loadingReg);

        loadingReg.setVisibility(View.GONE);
        btn_register.setVisibility(View.VISIBLE);

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
