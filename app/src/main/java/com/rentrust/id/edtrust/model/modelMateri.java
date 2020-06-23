package com.rentrust.id.edtrust.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class modelMateri {
    @Expose
    @SerializedName("id_materi") private int id_materi;
    @Expose
    @SerializedName("id_guru") private int id_guru;
    @Expose
    @SerializedName("nama_guru") private String nama_guru;
    @Expose
    @SerializedName("nama_materi") private String nama_materi;
    @Expose
    @SerializedName("keterangan") private String keterangan;
    @Expose
    @SerializedName("jenis_file") private String jenis_file;
    @Expose
    @SerializedName("tgl_materi") private String tgl_materi;
    @Expose
    @SerializedName("jam_materi") private String jam_materi;
    @Expose
    @SerializedName("nama_room") private String nama_room;
    @Expose
    @SerializedName("file") private String file;
    @Expose
    @SerializedName("str_thumbs") private String str_thumbs;
    boolean boolean_selected;
    @Expose
    @SerializedName("success") private Boolean success;
    @Expose
    @SerializedName("message") private String message;

    public int getId_materi() {
        return id_materi;
    }

    public void setId_materi(int id_materi) {
        this.id_materi = id_materi;
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

    public String getNama_materi() {
        return nama_materi;
    }

    public void setNama_materi(String nama_materi) {
        this.nama_materi = nama_materi;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getJenis_file() {
        return jenis_file;
    }

    public void setJenis_file(String jenis_file) {
        this.jenis_file = jenis_file;
    }

    public String getTgl_materi() {
        return tgl_materi;
    }

    public void setTgl_materi(String tgl_materi) {
        this.tgl_materi = tgl_materi;
    }

    public String getJam_materi() {
        return jam_materi;
    }

    public void setJam_materi(String jam_materi) {
        this.jam_materi = jam_materi;
    }

    public String getNama_room() {
        return nama_room;
    }

    public void setNama_room(String nama_room) {
        this.nama_room = nama_room;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getStr_thumbs() {
        return str_thumbs;
    }

    public void setStr_thumbs(String str_thumbs) {
        this.str_thumbs = str_thumbs;
    }

    public boolean isBoolean_selected() {
        return boolean_selected;
    }

    public void setBoolean_selected(boolean boolean_selected) {
        this.boolean_selected = boolean_selected;
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
