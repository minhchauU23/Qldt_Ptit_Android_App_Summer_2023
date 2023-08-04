package com.example.qldt_ptit_android_app_summer_2023.model

import com.google.gson.annotations.SerializedName

data class ScoreRespone(
    var data: RawScoreRespone,
    var result: Boolean,
    var code: Int
) {

    data class RawScoreRespone(
        @SerializedName("total_items")
        var totalItem: Int,
        @SerializedName("total_pages")
        var totalPage: Int,
        @SerializedName("ds_diem_hocky")
        var listScore: ArrayList<RawHocKY>
    ){

    }

    data class RawHocKY(
        @SerializedName("hoc_ky")
        var hocKy: String,
        @SerializedName("ten_hoc_ky")
        var name: String,
        @SerializedName("ds_diem_mon_hoc")
        var listScore: ArrayList<RawScore>

    ){

    }
    data class RawScore(
        @SerializedName("ma_mon")
        var subID: String,
        @SerializedName("ten_mon")
        var subName: String,
        @SerializedName("so_tin_chi")
        var numOfCredit: String,
        @SerializedName("nhom_to")
        var group: String,
        @SerializedName("diem_thi")
        var examScore: String,
        @SerializedName("diem_giua_ky")
        var midtermScore: String,
        @SerializedName("diem_tk")
        var finalScore: String,
        @SerializedName("diem_tk_so")
        var finalScoreNum: String,
        @SerializedName("diem_tk_chu")
        var finalScoreChar: String
    ){

    }

}
