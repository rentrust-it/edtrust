package com.rentrust.id.edtrust.siswa.tugas;

import androidx.annotation.NonNull;

import com.rentrust.id.edtrust.api.ApiClient;
import com.rentrust.id.edtrust.api.ApiInterface;
import com.rentrust.id.edtrust.model.modelNilai;
import com.rentrust.id.edtrust.model.modelRoomSiswa;
import com.rentrust.id.edtrust.model.modelSoal;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TugasPresenter {
    private TugasView view;

    public TugasPresenter(TugasView view) {
        this.view = view;
    }

    void getTugas(final int id_room, final int id_guru) {
        view.showLoading();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<modelSoal>> call = apiInterface.getTugas(id_room, id_guru);
        call.enqueue(new Callback<List<modelSoal>>() {
            @Override
            public void onResponse(@NonNull Call<List<modelSoal>> call, @NonNull Response<List<modelSoal>> response) {
                view.hideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    view.onGetTugas(response.body());
                }

            }

            @Override
            public void onFailure(@NonNull Call<List<modelSoal>> call, @NonNull Throwable t) {
                view.hideLoading();
                view.onRequestError(t.getLocalizedMessage());

            }
        });
    }

    void cekTugas(final int id_soal, final String nisn) {
        view.showLoading();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<modelNilai>> call = apiInterface.cekTugas(id_soal, nisn);
        call.enqueue(new Callback<List<modelNilai>>() {
            @Override
            public void onResponse(@NonNull Call<List<modelNilai>> call, @NonNull Response<List<modelNilai>> response) {
                view.hideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    view.onCekTugas(response.body());
                }

            }

            @Override
            public void onFailure(@NonNull Call<List<modelNilai>> call, @NonNull Throwable t) {
                view.hideLoading();
                view.onRequestError(t.getLocalizedMessage());

            }
        });
    }

    void joinTugas(final String nisn, final String kunci, final int id_soal) {

        view.showLoading();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<modelSoal> call = apiInterface.joinTugas(nisn, kunci, id_soal);

        call.enqueue(new Callback<modelSoal>() {
            @Override
            public void onResponse(@NonNull Call<modelSoal> call, @NonNull Response<modelSoal> response) {
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
            public void onFailure(@NonNull Call<modelSoal> call, @NonNull Throwable t) {
                view.hideLoading();
                view.onRequestError(t.getLocalizedMessage());

            }
        });
    }
}
