package com.rentrust.id.edtrust.siswa.tugas;

import com.rentrust.id.edtrust.model.modelNilai;
import com.rentrust.id.edtrust.model.modelSoal;

import java.util.List;

public interface TugasView {
    void showLoading();
    void hideLoading();
    void onGetTugas(List<modelSoal> soals);
    void onCekTugas(List<modelNilai> scores);
    void onRequestSuccess(String message);
    void onRequestError(String message);
}
