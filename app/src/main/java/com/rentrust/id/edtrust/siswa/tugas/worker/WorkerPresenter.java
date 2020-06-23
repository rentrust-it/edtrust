package com.rentrust.id.edtrust.siswa.tugas.worker;

import androidx.annotation.NonNull;

import com.rentrust.id.edtrust.api.ApiClient;
import com.rentrust.id.edtrust.api.ApiInterface;
import com.rentrust.id.edtrust.model.modelNilai;
import com.rentrust.id.edtrust.model.modelPGanda;
import com.rentrust.id.edtrust.model.modelSoal;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WorkerPresenter {
    private WorkerView view;

    public WorkerPresenter(WorkerView view) {
        this.view = view;
    }

    void getSoal(final int id_soal) {
        view.showLoading();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<modelPGanda>> call = apiInterface.getSoal(id_soal);
        call.enqueue(new Callback<List<modelPGanda>>() {
            @Override
            public void onResponse(@NonNull Call<List<modelPGanda>> call,@NonNull Response<List<modelPGanda>> response) {
                view.hideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    view.onGetSoal(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<modelPGanda>> call, @NonNull Throwable t) {
                view.hideLoading();
                view.onRequestError(t.getLocalizedMessage());

            }
        });
    }

    void addNilai(final String nisn, final int id_guru, final int id_soal, final int nilai ) {

        view.showLoading();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<modelNilai> call = apiInterface.addNilai(nisn, id_guru, id_soal, nilai);

        call.enqueue(new Callback<modelNilai>() {
            @Override
            public void onResponse(@NonNull Call<modelNilai> call, @NonNull Response<modelNilai> response) {
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
            public void onFailure(@NonNull Call<modelNilai> call, @NonNull Throwable t) {
                view.hideLoading();
                view.onRequestError(t.getLocalizedMessage());

            }
        });
    }

}
