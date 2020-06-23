package com.rentrust.id.edtrust.siswa.profile;

import androidx.annotation.NonNull;

import com.rentrust.id.edtrust.api.ApiClient;
import com.rentrust.id.edtrust.api.ApiInterface;
import com.rentrust.id.edtrust.model.modelSiswa;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePresenter {
    private ProfileView view;

    public ProfilePresenter(ProfileView view) {
        this.view = view;
    }

    void changePhoto(final String nisn, final String photo) {

        view.showLoading();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<modelSiswa> call = apiInterface.changePhoto(nisn, photo);

        call.enqueue(new Callback<modelSiswa>() {
            @Override
            public void onResponse(@NonNull Call<modelSiswa> call, @NonNull Response<modelSiswa> response) {


                if (response.isSuccessful() && response.body() != null) {

                    Boolean success =  response.body().getSuccess();
                    if (success) {
                        view.hideLoading();
                        view.onRequestSuccessUpload(response.body().getMessage());
                    } else {
                        view.hideLoading();
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

    void changeName(final String nisn, final String nama) {

        view.showLoading();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<modelSiswa> call = apiInterface.changeName(nisn, nama);

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

    void changePassword(final String nisn, final String curr_pass, final String new_pass) {

        view.showLoading();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<modelSiswa> call = apiInterface.changePassword(nisn, curr_pass, new_pass);

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

    void getPhotoProfile(final String nisn) {
        view.showLoading();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<modelSiswa>> call = apiInterface.getPhotoProfile(nisn);
        call.enqueue(new Callback<List<modelSiswa>>() {
            @Override
            public void onResponse(@NonNull Call<List<modelSiswa>> call, @NonNull Response<List<modelSiswa>> response) {
                view.hideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    view.onGetProfile(response.body());
                }

            }

            @Override
            public void onFailure(@NonNull Call<List<modelSiswa>> call, @NonNull Throwable t) {
                view.hideLoading();
                view.onRequestError(t.getLocalizedMessage());

            }
        });
    }
}
