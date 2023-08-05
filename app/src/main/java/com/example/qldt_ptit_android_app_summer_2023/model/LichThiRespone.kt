package com.example.qldt_ptit_android_app_summer_2023.model

import com.google.gson.annotations.SerializedName

data class LichThiRespone(
    @SerializedName("data")
    var data: RawData

) {
    data class RawData(
        @SerializedName("ds_lich_thi")
        var listLichThi : ArrayList<LichThiRaw>
    ){

    }


    data class LichThiRaw(
        @SerializedName("ghep_thi")
        var id: String,
        @SerializedName("ma_mon")
        var subID: String,
        @SerializedName("ma_phong")
        var room: String,
        @SerializedName("ngay_thi")
        var date: String,
        @SerializedName("gio_bat_dau")
        var startTime: String,
        @SerializedName("so_phut")
        var minutes: String,
        @SerializedName("hinh_thuc_thi")
        var format: String
    ){

    }

}