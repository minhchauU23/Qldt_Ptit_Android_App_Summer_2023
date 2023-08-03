package com.example.qldt_ptit_android_app_summer_2023.model

import com.google.gson.annotations.SerializedName

data class Tuan(
    var tuanHocKy: Int,
    var tuanTuyetDoi: Int,
    var description: String,
    var startDate: String,
    var endDate: String,
    var hocKy: HocKy
) {
    override fun toString(): String {
        return "Tuần $tuanHocKy từ $startDate đến $endDate"
    }
}