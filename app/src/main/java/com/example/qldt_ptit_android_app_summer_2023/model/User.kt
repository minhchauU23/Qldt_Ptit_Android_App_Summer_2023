package com.example.qldt_ptit_android_app_summer_2023.model

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET

class User(var username: String,var password: String): java.io.Serializable {
    @SerializedName("name")
    var fullName: String? = null
    lateinit var roles: String
    @SerializedName("access_token")
    lateinit var accessToken: String
    @SerializedName("token_type")
    lateinit var tokenType: String
    fun isValidUsername(): Boolean{
        return !username.equals("")
    }

    fun isValidPassword(): Boolean{
        return !password.equals("")
    }

    fun isInitialized(): Boolean = this::accessToken.isInitialized

}