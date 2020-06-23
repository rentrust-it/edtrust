package com.rentrust.id.edtrust.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class modelSoal {
    @Expose
    @SerializedName("id_room") private int id_room;
    @Expose
    @SerializedName("id_soal") private int id_soal;
    @Expose
    @SerializedName("nama_soal") private String nama_soal;
    @Expose
    @SerializedName("id_guru") private int id_guru;
    @Expose
    @SerializedName("nama_guru") private String nama_guru;
    @Expose
    @SerializedName("tgl_soal") private String tgl_soal;
    @Expose
    @SerializedName("jam_soal") private String jam_soal;
    @Expose
    @SerializedName("type_soal") private String type_soal;
    @Expose
    @SerializedName("jenis_soal") private String jenis_soal;
    @Expose
    @SerializedName("jumlah_soal") private int jumlah_soal;
    @Expose
    @SerializedName("durasi") private int durasi;
    @Expose
    @SerializedName("kunci") private String kunci;
    @Expose
    @SerializedName("score") private int score;
    @Expose
    @SerializedName("nisn") private String nisn;
    @Expose
    @SerializedName("status") private String status;
    @Expose
    @SerializedName("color") private String color;
    @Expose
    @SerializedName("success") private Boolean success;
    @Expose
    @SerializedName("message") private String message;

    public int getId_room() {
        return id_room;
    }

    public void setId_room(int id_room) {
        this.id_room = id_room;
    }

    public int getId_soal() {
        return id_soal;
    }

    public void setId_soal(int id_soal) {
        this.id_soal = id_soal;
    }

    public String getNama_soal() {
        return nama_soal;
    }

    public void setNama_soal(String nama_soal) {
        this.nama_soal = nama_soal;
    }

    public int getId_guru() {
        return id_guru;
    }

    public void setId_guru(int id_guru) {
        this.id_guru = id_guru;
    }

    public String getNama_guru() {
        return nama_guru;
    }

    public void setNama_guru(String nama_guru) {
        this.nama_guru = nama_guru;
    }

    public String getTgl_soal() {
        return tgl_soal;
    }

    public void setTgl_soal(String tgl_soal) {
        this.tgl_soal = tgl_soal;
    }

    public String getJam_soal() {
        return jam_soal;
    }

    public void setJam_soal(String jam_soal) {
        this.jam_soal = jam_soal;
    }

    public String getType_soal() {
        return type_soal;
    }

    public void setType_soal(String type_soal) {
        this.type_soal = type_soal;
    }

    public String getJenis_soal() {
        return jenis_soal;
    }

    public void setJenis_soal(String jenis_soal) {
        this.jenis_soal = jenis_soal;
    }

    public int getJumlah_soal() {
        return jumlah_soal;
    }

    public void setJumlah_soal(int jumlah_soal) {
        this.jumlah_soal = jumlah_soal;
    }

    public int getDurasi() {
        return durasi;
    }

    public void setDurasi(int durasi) {
        this.durasi = durasi;
    }

    public String getKunci() {
        return kunci;
    }

    public void setKunci(String kunci) {
        this.kunci = kunci;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNisn() {
        return nisn;
    }

    public void setNisn(String nisn) {
        this.nisn = nisn;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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
