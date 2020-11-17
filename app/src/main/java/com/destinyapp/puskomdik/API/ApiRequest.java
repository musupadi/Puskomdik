package com.destinyapp.puskomdik.API;



import com.destinyapp.puskomdik.Model.ResponseModel;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiRequest {
    @FormUrlEncoded
    @POST("auth/login")
    Call<ResponseModel> login(@Field("username") String username,
                              @Field("password") String password);

    @FormUrlEncoded
    @POST("apply_ekskul")
    Call<ResponseModel> ApplyEskul(@Header("Authorization") String authHeader,
            @Field("idEkskul[]") String idEskul);

    @Multipart
    @POST("ubahphotoprofil")
    Call<ResponseModel> ChangeFoto(@Header("Authorization") String authHeader,
                                @Part MultipartBody.Part photo);
    @FormUrlEncoded
    @POST("ubahpassword")
    Call<ResponseModel> UbahPassword(@Header("Authorization") String authHeader,
                                   @Field("passwordNew") String passwordNew,
                                     @Field("passwordConfirm") String passwordConfirm,
                                     @Field("passwordOld") String passwordOld);
    //
    
    @GET("kabarsekolah")
    Call<ResponseModel> KabarSekolah(@Header("Authorization") String authHeader);

    @GET("strukturoganisasi")
    Call<ResponseModel> StrukturSekolah(@Header("Authorization") String authHeader);

    @GET("prestasi")
    Call<ResponseModel> Prestasi(@Header("Authorization") String authHeader);

    @GET("ekskul?self=true")
    Call<ResponseModel> EskulAll(@Header("Authorization") String authHeader);

    @GET("ekskul_self")
    Call<ResponseModel> EskulSelf(@Header("Authorization") String authHeader);

    @GET("getmapelkelasjadwal")
    Call<ResponseModel> GetMapel(@Header("Authorization") String authHeader,
                                  @Query("tglKelas") String tglKelas);

    @GET("teman")
    Call<ResponseModel> Teman(@Header("Authorization") String authHeader,
                                 @Query("idKelas") String idKelas);

    @GET("raport")
    Call<ResponseModel> Raport(@Header("Authorization") String authHeader);


    @GET("poinsiswa")
    Call<ResponseModel> PointSiswa(@Header("Authorization") String authHeader);

    @GET("agenda_sekolah")
    Call<ResponseModel> AgendaSekolah(@Header("Authorization") String authHeader);

}
