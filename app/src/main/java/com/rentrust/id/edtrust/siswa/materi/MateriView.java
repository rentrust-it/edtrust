package com.rentrust.id.edtrust.siswa.materi;

import com.rentrust.id.edtrust.model.modelMateri;

import java.util.List;

public interface MateriView {
    void showLoading();
    void hideLoading();
    void onGetFile(List<modelMateri> models);
    void onRequestSuccess(String message);
    void onRequestError(String message);
}
