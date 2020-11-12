package com.destinyapp.puskomdik.API;



import com.destinyapp.puskomdik.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiRequest {
    @FormUrlEncoded
    @POST("auth/login")
    Call<ResponseModel> login(@Field("username") String username,
                              @Field("password") String password);

    //GET
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

}
