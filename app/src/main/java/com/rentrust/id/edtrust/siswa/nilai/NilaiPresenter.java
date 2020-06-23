package com.rentrust.id.edtrust.siswa.nilai;

import androidx.annotation.NonNull;

import com.rentrust.id.edtrust.api.ApiClient;
import com.rentrust.id.edtrust.api.ApiInterface;
import com.rentrust.id.edtrust.model.modelNilai;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NilaiPresenter {
    private NilaiView view;

    public NilaiPresenter(NilaiView view) {
        this.view = view;
    }

    void getNilai(final String nisn) {
        view.showLoading();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<modelNilai>> call = apiInterface.getNilai(nisn);
        call.enqueue(new Callback<List<modelNilai>>() {
            @Override
            public void onResponse(@NonNull Call<List<modelNilai>> call, @NonNull Response<List<modelNilai>> response) {
                view.hideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    view.onGetNilai(response.body());
                }

            }

            @Override
            public void onFailure(@NonNull Call<List<modelNilai>> call, @NonNull Throwable t) {
                view.hideLoading();
                view.onRequestError(t.getLocalizedMessage());

            }
        });
    }
}
