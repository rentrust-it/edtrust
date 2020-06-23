package com.rentrust.id.edtrust.siswa.history;

import com.rentrust.id.edtrust.model.modelHistory;

import java.util.List;

public interface HistoryView {
    void showLoading();
    void hideLoading();
    void onGetHistory(List<modelHistory> histories);
    void onRequestSuccess(String message);
    void onRequestError(String message);
}
