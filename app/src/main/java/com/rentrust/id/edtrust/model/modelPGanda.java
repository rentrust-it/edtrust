package com.rentrust.id.edtrust.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class modelPGanda {
    @Expose
    @SerializedName("id_soal") private int id_soal;
    @Expose
    @SerializedName("id_guru") private int id_guru;
    @Expose
    @SerializedName("nomor") private int nomor;
    @Expose
    @SerializedName("pertanyaan") private String pertanyaan;
    @Expose
    @SerializedName("a") private String a;
    @Expose
    @SerializedName("b") private String b;
    @Expose
    @SerializedName("c") private String c;
    @Expose
    @SerializedName("d") private String d;
    @Expose
    @SerializedName("e") private String e;
    @Expose
    @SerializedName("kunci") private String kunci;
    @Expose
    @SerializedName("jawaban") private String jawaban;
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

    public int getNomor() {
        return nomor;
    }

    public void setNomor(int nomor) {
        this.nomor = nomor;
    }

    public String getPertanyaan() {
        return pertanyaan;
    }

    public void setPertanyaan(String pertanyaan) {
        this.pertanyaan = pertanyaan;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public String getKunci() {
        return kunci;
    }

    public void setKunci(String kunci) {
        this.kunci = kunci;
    }

    public String getJawaban() {
        return jawaban;
    }

    public void setJawaban(String jawaban) {
        this.jawaban = jawaban;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getNisn() {
        return nisn;
    }

    public void setNisn(String nisn) {
        this.nisn = nisn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
