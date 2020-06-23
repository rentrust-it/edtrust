package com.rentrust.id.edtrust.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class modelNilai {
    @Expose
    @SerializedName("id_nilai") private int id_nilai;
    @Expose
    @SerializedName("id_soal") private int id_soal;
    @Expose
    @SerializedName("id_guru") private int id_guru;
    @Expose
    @SerializedName("nama_soal") private String nama_soal;
    @Expose
    @SerializedName("nama_guru") private String nama_guru;
    @Expose
    @SerializedName("type_soal") private String type_soal;
    @Expose
    @SerializedName("jenis_soal") private String jenis_soal;
    @Expose
    @SerializedName("nisn") private String nisn;
    @Expose
    @SerializedName("nilai") private int nilai;
    @Expose
    @SerializedName("tgl") private String tgl;
    @Expose
    @SerializedName("jam") private String jam;
    @Expose
    @SerializedName("report") private int report;
    @Expose
    @SerializedName("success") private Boolean success;
    @Expose
    @SerializedName("message") private String message;

    public int getId_nilai() {
        return id_nilai;
    }

    public void setId_nilai(int id_nilai) {
        this.id_nilai = id_nilai;
    }

    public int getId_soal() {
        return id_soal;
    }

    public void setId_soal(int id_soal) {
        this.id_soal = id_soal;
    }

    public int getId_guru() {
        return id_guru;
    }

    public void setId_guru(int id_guru) {
        this.id_guru = id_guru;
    }

    public String getNisn() {
        return nisn;
    }

    public void setNisn(String nisn) {
        this.nisn = nisn;
    }

    public String getNama_soal() {
        return nama_soal;
    }

    public void setNama_soal(String nama_soal) {
        this.nama_soal = nama_soal;
    }

    public String getNama_guru() {
        return nama_guru;
    }

    public void setNama_guru(String nama_guru) {
        this.nama_guru = nama_guru;
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

    public int getNilai() {
        return nilai;
    }

    public void setNilai(int nilai) {
        this.nilai = nilai;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public int getReport() {
        return report;
    }

    public void setReport(int report) {
        this.report = report;
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
