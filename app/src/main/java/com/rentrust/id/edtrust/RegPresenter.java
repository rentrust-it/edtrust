package com.rentrust.id.edtrust;

import androidx.annotation.NonNull;

import com.rentrust.id.edtrust.api.ApiClient;
import com.rentrust.id.edtrust.api.ApiInterface;
import com.rentrust.id.edtrust.model.modelSiswa;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegPresenter {
    private RegView view;

    public RegPresenter(RegView view) {
        this.view = view;
    }

    void registerSiswa(final String nisn, final String username, final String password, final String nama, final String jk, final String tgl_lahir) {

        view.showLoading();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<modelSiswa> call = apiInterface.regSiswa(nisn, username, password, nama, jk, tgl_lahir);

        call.enqueue(new Callback<modelSiswa>() {
            @Override
            public void onResponse(@NonNull Call<modelSiswa> call, @NonNull Response<modelSiswa> response) {
                view.hideLoading();

                if (response.isSuccessful() && response.body() != null) {

                    Boolean success =  response.body().getSuccess();
                    if (success) {
                        view.onRequestSuccess(response.body().getMessage());
                    } else {
                        view.onRequestError(response.body().getMessage());
                    }

                }

            }

            @Override
            public void onFailure(@NonNull Call<modelSiswa> call, @NonNull Throwable t) {
                view.hideLoading();
                view.onRequestError(t.getLocalizedMessage());

            }
        });
    }

}