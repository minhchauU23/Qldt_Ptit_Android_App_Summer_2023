package com.example.qldt_ptit_android_app_summer_2023.api

import com.example.qldt_ptit_android_app_summer_2023.model.User
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface QldtService {
    companion object{
        var retrofit: QldtService = Retrofit.Builder()
            .baseUrl("https://qldt.ptit.edu.vn/api/")
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create()

    }

    @POST("auth/login")
    @FormUrlEncoded
    fun login(@Field("username") username: String, @Field("password") password: String,
    @Field("grant_type") grantType: String = "password") : Call<User>

}