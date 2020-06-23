package com.rentrust.id.edtrust.siswa.materi;

import androidx.annotation.NonNull;

import com.rentrust.id.edtrust.api.ApiClient;
import com.rentrust.id.edtrust.api.ApiInterface;
import com.rentrust.id.edtrust.model.modelMateri;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MateriPresenter {
    private MateriView view;

    public MateriPresenter(MateriView view) {
        this.view = view;
    }

    void getFile(final String nama_room, final int id_guru) {
        view.showLoading();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<modelMateri>> call = apiInterface.getFile(nama_room, id_guru);
        call.enqueue(new Callback<List<modelMateri>>() {
            @Override
            public void onResponse(@NonNull Call<List<modelMateri>> call, @NonNull Response<List<modelMateri>> response) {
                view.hideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    view.onGetFile(response.body());
                }

            }

            @Override
            public void onFailure(@NonNull Call<List<modelMateri>> call, @NonNull Throwable t) {
                view.hideLoading();
                view.onRequestError(t.getLocalizedMessage());

            }
        });
    }

    void getImage(final String nama_room, final int id_guru) {
        view.showLoading();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<modelMateri>> call = apiInterface.getImage(nama_room, id_guru);
        call.enqueue(new Callback<List<modelMateri>>() {
            @Override
            public void onResponse(@NonNull Call<List<modelMateri>> call, @NonNull Response<List<modelMateri>> response) {
                view.hideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    view.onGetFile(response.body());
                }

            }

            @Override
            public void onFailure(@NonNull Call<List<modelMateri>> call, @NonNull Throwable t) {
                view.hideLoading();
                view.onRequestError(t.getLocalizedMessage());

            }
        });
    }

    void getVideo(final String nama_room, final int id_guru) {
        view.showLoading();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<modelMateri>> call = apiInterface.getVideo(nama_room, id_guru);
        call.enqueue(new Callback<List<modelMateri>>() {
            @Override
            public void onResponse(@NonNull Call<List<modelMateri>> call, @NonNull Response<List<modelMateri>> response) {
                view.hideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    view.onGetFile(response.body());
                }

            }

            @Override
            public void onFailure(@NonNull Call<List<modelMateri>> call, @NonNull Throwable t) {
                view.hideLoading();
                view.onRequestError(t.getLocalizedMessage());

            }
        });
    }
}
