package com.rentrust.id.edtrust.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class modelSiswa {
    @Expose
    @SerializedName("username") private String username;
    @Expose
    @SerializedName("password") private String password;
    @Expose
    @SerializedName("nisn") private String nisn;
    @Expose
    @SerializedName("nama") private String nama;
    @Expose
    @SerializedName("jk") private String jk;
    @Expose
    @SerializedName("tgl_lahir") private String tgl_lahir;
    @Expose
    @SerializedName("foto") private String foto;
    @Expose
    @SerializedName("success") private Boolean success;
    @Expose
    @SerializedName("message") private String message;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNisn() {
        return nisn;
    }

    public void setNisn(String nisn) {
        this.nisn = nisn;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJk() {
        return jk;
    }

    public void setJk(String jk) {
        this.jk = jk;
    }

    public String getTgl_lahir() {
        return tgl_lahir;
    }

    public void setTgl_lahir(String tgl_lahir) {
        this.tgl_lahir = tgl_lahir;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
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
