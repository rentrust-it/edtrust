package com.rentrust.id.edtrust.siswa.profile;

import com.rentrust.id.edtrust.model.modelSiswa;

import java.util.List;

public interface ProfileView {
    void showLoading();
    void hideLoading();
    void onGetProfile(List<modelSiswa> siswas);
    void onRequestSuccess(String message);
    void onRequestSuccessUpload(String message);
    void onRequestError(String message);
}
