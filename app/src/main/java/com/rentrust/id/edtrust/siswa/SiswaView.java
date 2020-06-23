package com.rentrust.id.edtrust.siswa;

import com.rentrust.id.edtrust.model.modelRoomSiswa;

import java.util.List;

public interface SiswaView {
    void showLoading();
    void hideLoading();
    void onGetRoomSiswa(List<modelRoomSiswa> roomSiswas);
    void onRequestSuccess(String message);
    void onRequestError(String message);
}
