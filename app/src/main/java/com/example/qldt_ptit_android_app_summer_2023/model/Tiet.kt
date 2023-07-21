package com.example.qldt_ptit_android_app_summer_2023.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Tiet(
    var tiet: Int,
    @SerializedName("gio_bat_dau")
    var startTime: String,
    @SerializedName("gio_ket_thuc")
    var endTime: String,
    @SerializedName("nhhk")
    var hkid: Int
    ) {

}