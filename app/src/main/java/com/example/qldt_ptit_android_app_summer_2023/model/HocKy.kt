package com.example.qldt_ptit_android_app_summer_2023.model

import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.Date

data class HocKy(
    @SerializedName("hoc_ky")
    var id: Int,
    @SerializedName("ten_hoc_ky")
    var description: String,
    @SerializedName("ngay_bat_dau_hk")
    var startDate: String,
    @SerializedName("ngay_ket_thuc_hk")
    var endDate: String
) {


    fun getStartDate(): Date{
        return SimpleDateFormat("dd/MM/yyyy").parse(startDate)
    }

    fun getEndDate(): Date{
        return SimpleDateFormat("dd/MM/yyyy").parse(endDate)
    }

    override fun toString(): String {
        return description
    }
}