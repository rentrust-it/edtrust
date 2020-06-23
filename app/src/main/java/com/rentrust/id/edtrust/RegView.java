package com.rentrust.id.edtrust;

import com.rentrust.id.edtrust.model.modelSiswa;

import java.util.List;

public interface RegView {
    void showLoading();
    void hideLoading();
    void onGetSiswa(List<modelSiswa> siswas);
    void onRequestSuccess(String message);
    void onRequestError(String message);
}
