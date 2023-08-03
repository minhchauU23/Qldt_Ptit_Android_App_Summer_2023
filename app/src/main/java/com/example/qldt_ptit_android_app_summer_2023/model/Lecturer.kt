package com.example.qldt_ptit_android_app_summer_2023.model

import com.google.gson.annotations.SerializedName

data class Lecturer(
    @SerializedName("ma_giang_vien")
    var lecturerCode : String,
    @SerializedName("ten_giang_vien")
    var name: String
) {
}