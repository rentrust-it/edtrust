package com.rentrust.id.edtrust.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class modelRoomSiswa {
    @Expose
    @SerializedName("nisn") private String nisn;
    @Expose
    @SerializedName("id_guru") private int id_guru;
    @Expose
    @SerializedName("nama_murid") private String nama_murid;
    @Expose
    @SerializedName("nama_guru") private String nama_guru;
    @Expose
    @SerializedName("my_room") private int my_room;
    @Expose
    @SerializedName("id_room") private int id_room;
    @Expose
    @SerializedName("kode_room") private String kode_room;
    @Expose
    @SerializedName("nama_room") private String nama_room;
    @Expose
    @SerializedName("jumlah_peserta") private int jumlah_peserta;
    @Expose
    @SerializedName("status_room") private String status_room;
    @Expose
    @SerializedName("success") private Boolean success;
    @Expose
    @SerializedName("message") private String message;

    public String getNisn() {
        return nisn;
    }

    public void setNisn(String nisn) {
        this.nisn = nisn;
    }

    public int getId_guru() {
        return id_guru;
    }

    public void setId_guru(int id_guru) {
        this.id_guru = id_guru;
    }

    public String getNama_murid() {
        return nama_murid;
    }

    public void setNama_murid(String nama_murid) {
        this.nama_murid = nama_murid;
    }

    public String getNama_guru() {
        return nama_guru;
    }

    public void setNama_guru(String nama_guru) {
        this.nama_guru = nama_guru;
    }

    public int getMy_room() {
        return my_room;
    }

    public void setMy_room(int my_room) {
        this.my_room = my_room;
    }

    public int getId_room() {
        return id_room;
    }

    public void setId_room(int id_room) {
        this.id_room = id_room;
    }

    public String getKode_room() {
        return kode_room;
    }

    public void setKode_room(String kode_room) {
        this.kode_room = kode_room;
    }

    public String getNama_room() {
        return nama_room;
    }

    public void setNama_room(String nama_room) {
        this.nama_room = nama_room;
    }

    public int getJumlah_peserta() {
        return jumlah_peserta;
    }

    public void setJumlah_peserta(int jumlah_peserta) {
        this.jumlah_peserta = jumlah_peserta;
    }

    public String getStatus_room() {
        return status_room;
    }

    public void setStatus_room(String status_room) {
        this.status_room = status_room;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
