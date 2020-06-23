package com.rentrust.id.edtrust.siswa.room;

import com.rentrust.id.edtrust.model.modelRoomSiswa;

import java.util.List;

public interface RoomView {
    void showLoading();
    void hideLoading();
    void onGetMyRoom(List<modelRoomSiswa> rooms);
    void onRequestSuccess(String message);
    void onRequestError(String message);
}
