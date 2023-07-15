package com.example.qldt_ptit_android_app_summer_2023.api

import com.example.qldt_ptit_android_app_summer_2023.model.User
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface QldtService {
    companion object{
        var gson: Gson = GsonBuilder().setDateFormat("dd/MM/yyyy HH:mm").create()
        var retrofit: QldtService = Retrofit.Builder()
            .baseUrl("https://qldt.ptit.edu.vn/api/")
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create()

    }

    @POST("auth/login")
    @FormUrlEncoded
    suspend fun login(@Field("username") username: String, @Field("password") password: String,
              @Field("grant_type") grantType: String = "password") : Response<User>
}