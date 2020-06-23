package com.rentrust.id.edtrust.siswa;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.rentrust.id.edtrust.api.ApiClient;
import com.rentrust.id.edtrust.api.ApiInterface;
import com.rentrust.id.edtrust.model.modelRoomSiswa;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SiswaPresenter {
    private SiswaView view;

    public SiswaPresenter(SiswaView view) {
        this.view = view;
    }

    void getInfoRoom(final String nisn) {
        view.showLoading();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<modelRoomSiswa>> call = apiInterface.getInfoRoomSiswa(nisn);
        call.enqueue(new Callback<List<modelRoomSiswa>>() {
            @Override
            public void onResponse(@NonNull Call<List<modelRoomSiswa>> call, @NonNull Response<List<modelRoomSiswa>> response) {
                view.hideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    view.onGetRoomSiswa(response.body());
                }

            }

            @Override
            public void onFailure(@NonNull Call<List<modelRoomSiswa>> call, @NonNull Throwable t) {
                view.hideLoading();
                view.onRequestError(t.getLocalizedMessage());

            }
        });
    }
}
