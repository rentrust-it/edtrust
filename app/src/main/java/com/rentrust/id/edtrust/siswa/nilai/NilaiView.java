package com.rentrust.id.edtrust.siswa.nilai;

import com.rentrust.id.edtrust.model.modelNilai;

import java.util.List;

public interface NilaiView {
    void showLoading();
    void hideLoading();
    void onGetNilai(List<modelNilai> scores);
    void onRequestSuccess(String message);
    void onRequestError(String message);
}
