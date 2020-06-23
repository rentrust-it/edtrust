package com.rentrust.id.edtrust.siswa.tugas.worker;

import com.rentrust.id.edtrust.model.modelPGanda;
import com.rentrust.id.edtrust.model.modelSoal;

import java.util.List;

public interface WorkerView{
    void showLoading();
    void hideLoading();
    void onGetSoal(List<modelPGanda> gandas);
    void onRequestSuccess(String message);
    void onRequestError(String message);
}
