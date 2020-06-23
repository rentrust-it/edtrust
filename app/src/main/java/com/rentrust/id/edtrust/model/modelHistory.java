package com.rentrust.id.edtrust.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class modelHistory {
    @Expose
    @SerializedName("id_history") private int id_history;
    @Expose
    @SerializedName("nisn") private String nisn;
    @Expose
    @SerializedName("judul") private String judul;
    @Expose
    @SerializedName("tgl_history") private String tgl_history;
    @Expose
    @SerializedName("jam_history") private String jam_history;
    @Expose
    @SerializedName("success") private Boolean success;
    @Expose
    @SerializedName("message") private String message;

    public int getId_history() {
        return id_history;
    }

    public void setId_history(int id_history) {
        this.id_history = id_history;
    }

    public String getNisn() {
        return nisn;
    }

    public void setNisn(String nisn) {
        this.nisn = nisn;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getTgl_history() {
        return tgl_history;
    }

    public void setTgl_history(String tgl_history) {
        this.tgl_history = tgl_history;
    }

    public String getJam_history() {
        return jam_history;
    }

    public void setJam_history(String jam_history) {
        this.jam_history = jam_history;
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
