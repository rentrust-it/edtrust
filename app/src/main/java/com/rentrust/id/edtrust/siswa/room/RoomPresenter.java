package com.rentrust.id.edtrust.siswa.room;

import androidx.annotation.NonNull;

import com.rentrust.id.edtrust.api.ApiClient;
import com.rentrust.id.edtrust.api.ApiInterface;
import com.rentrust.id.edtrust.model.modelRoomSiswa;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoomPresenter {
    private RoomView view;

    public RoomPresenter(RoomView view) {
        this.view = view;
    }

    void getMyRoom(final String nisn) {
        view.showLoading();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<modelRoomSiswa>> call = apiInterface.getMyRoom(nisn);
        call.enqueue(new Callback<List<modelRoomSiswa>>() {
            @Override
            public void onResponse(@NonNull Call<List<modelRoomSiswa>> call, @NonNull Response<List<modelRoomSiswa>> response) {
                view.hideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    view.onGetMyRoom(response.body());
                }

            }

            @Override
            public void onFailure(@NonNull Call<List<modelRoomSiswa>> call, @NonNull Throwable t) {
                view.hideLoading();
                view.onRequestError(t.getLocalizedMessage());

            }
        });
    }

    void joinRoom(final String nisn, final String kode_room) {

        view.showLoading();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<modelRoomSiswa> call = apiInterface.joinRoom(nisn, kode_room);

        call.enqueue(new Callback<modelRoomSiswa>() {
            @Override
            public void onResponse(@NonNull Call<modelRoomSiswa> call, @NonNull Response<modelRoomSiswa> response) {
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
            public void onFailure(@NonNull Call<modelRoomSiswa> call, @NonNull Throwable t) {
                view.hideLoading();
                view.onRequestError(t.getLocalizedMessage());

            }
        });
    }

    void changeFav(final String nisn, final int my_room) {

        view.showLoading();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<modelRoomSiswa> call = apiInterface.changeFav(nisn, my_room);

        call.enqueue(new Callback<modelRoomSiswa>() {
            @Override
            public void onResponse(@NonNull Call<modelRoomSiswa> call, @NonNull Response<modelRoomSiswa> response) {
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
            public void onFailure(@NonNull Call<modelRoomSiswa> call, @NonNull Throwable t) {
                view.hideLoading();
                view.onRequestError(t.getLocalizedMessage());

            }
        });
    }
}
