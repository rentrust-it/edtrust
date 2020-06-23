package com.rentrust.id.edtrust.siswa.history;

import androidx.annotation.NonNull;

import com.rentrust.id.edtrust.api.ApiClient;
import com.rentrust.id.edtrust.api.ApiInterface;
import com.rentrust.id.edtrust.model.modelHistory;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryPresenter {
    private HistoryView view;

    public HistoryPresenter(HistoryView view) {
        this.view = view;
    }

    void getHistory(final String nisn) {
        view.showLoading();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<modelHistory>> call = apiInterface.getHistory(nisn);
        call.enqueue(new Callback<List<modelHistory>>() {
            @Override
            public void onResponse(@NonNull Call<List<modelHistory>> call, @NonNull Response<List<modelHistory>> response) {
                view.hideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    view.onGetHistory(response.body());
                }

            }

            @Override
            public void onFailure(@NonNull Call<List<modelHistory>> call, @NonNull Throwable t) {
                view.hideLoading();
                view.onRequestError(t.getLocalizedMessage());

            }
        });
    }

}
