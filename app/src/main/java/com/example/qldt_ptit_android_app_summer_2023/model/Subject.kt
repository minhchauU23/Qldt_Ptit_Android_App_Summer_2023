package com.example.qldt_ptit_android_app_summer_2023.model

import com.google.gson.annotations.SerializedName

data class Subject(
    @SerializedName("ma_mon")
    var subId: String,
    @SerializedName("ten_mon")
    var name: String,
    @SerializedName("so_tin_chi")
    var numOfCredit: Int
) {
}