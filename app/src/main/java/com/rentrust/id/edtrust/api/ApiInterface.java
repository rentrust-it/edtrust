package com.rentrust.id.edtrust.api;

import com.rentrust.id.edtrust.model.modelHistory;
import com.rentrust.id.edtrust.model.modelMateri;
import com.rentrust.id.edtrust.model.modelNilai;
import com.rentrust.id.edtrust.model.modelPGanda;
import com.rentrust.id.edtrust.model.modelRoomSiswa;
import com.rentrust.id.edtrust.model.modelSiswa;
import com.rentrust.id.edtrust.model.modelSoal;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("register-siswa")
    Call<modelSiswa> regSiswa(
            @Field("nisn") String nisn,
            @Field("username") String username,
            @Field("password") String password,
            @Field("nama") String nama,
            @Field("jk") String jk,
            @Field("tgl_lahir") String tgl_lahir

    );

    @FormUrlEncoded
    @POST("siswa/join-room")
    Call<modelRoomSiswa> joinRoom(
            @Field("nisn") String nisn,
            @Field("kode_room") String kode_room
    );

    @FormUrlEncoded
    @POST("siswa/join-tugas")
    Call<modelSoal> joinTugas(
            @Field("nisn") String nisn,
            @Field("kunci") String kunci,
            @Field("id_soal") int id_soal
    );

    @FormUrlEncoded
    @POST("siswa/change-fav")
    Call<modelRoomSiswa> changeFav(
            @Field("nisn") String nisn,
            @Field("my_room") int my_room
    );

    @FormUrlEncoded
    @POST("siswa/change-name")
    Call<modelSiswa> changeName(
            @Field("nisn") String nisn,
            @Field("nama") String nama
    );

    @FormUrlEncoded
    @POST("siswa/change-password")
    Call<modelSiswa> changePassword(
            @Field("nisn") String nisn,
            @Field("curr_pass") String curr_pass,
            @Field("new_pass") String new_pass
    );

    @FormUrlEncoded
    @POST("siswa/change-photo")
    Call<modelSiswa> changePhoto(
            @Field("nisn") String nisn,
            @Field("photo") String photo
    );

    @FormUrlEncoded
    @POST("siswa/add-nilai")
    Call<modelNilai> addNilai(
            @Field("nisn") String nisn,
            @Field("id_guru") int id_guru,
            @Field("id_soal") int id_soal,
            @Field("nilai") int nilai
    );

    @GET("siswa/info_room.php")
    Call<List<modelRoomSiswa>> getInfoRoomSiswa(
            @Query("nisn") String nisn
    );

    @GET("siswa/cek_room.php")
    Call<List<modelRoomSiswa>> getMyRoom(
            @Query("nisn") String nisn
    );

    @GET("siswa/get_tugas.php")
    Call<List<modelSoal>> getTugas(
            @Query("id_room") int id_room,
            @Query("id_guru") int id_guru
    );

    @GET("siswa/get_photo.php")
    Call<List<modelSiswa>> getPhotoProfile(
            @Query("nisn") String nisn
    );

    @GET("siswa/cek_tugas.php")
    Call<List<modelNilai>> cekTugas(
            @Query("id_soal") int id_soal,
            @Query("nisn") String nisn
    );

    @GET("siswa/get_soal.php")
    Call<List<modelPGanda>> getSoal(
            @Query("id_soal") int id_soal
    );

    @GET("siswa/get_nilai.php")
    Call<List<modelNilai>> getNilai(
            @Query("nisn") String nisn
    );

    @GET("siswa/get_file.php")
    Call<List<modelMateri>> getFile(
            @Query("nama_room") String nama_room,
            @Query("id_guru") int id_guru
    );

    @GET("siswa/get_image.php")
    Call<List<modelMateri>> getImage(
            @Query("nama_room") String nama_room,
            @Query("id_guru") int id_guru
    );

    @GET("siswa/get_video.php")
    Call<List<modelMateri>> getVideo(
            @Query("nama_room") String nama_room,
            @Query("id_guru") int id_guru
    );

    @GET("siswa/get_history.php")
    Call<List<modelHistory>> getHistory(
            @Query("nisn") String nisn
    );

    @GET("siswa/getting_soal.php")
    Call<List<modelSoal>> gettingSoal(
            @Query("id_soal") int id_soal
    );
/*
    @GET("laporan_admin.php")
    Call<List<modelTransaksi>> getLaporanAdmin(
            @Query("from") String from,
            @Query("to") String to
    );

    //Get Meja Kosong
    @GET("data_meja_kosong.php")
    Call<List<modelPemesanan>> getMejaKosong();
*/
}
