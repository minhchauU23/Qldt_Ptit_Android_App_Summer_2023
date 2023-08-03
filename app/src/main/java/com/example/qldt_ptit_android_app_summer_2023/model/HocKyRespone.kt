package com.example.qldt_ptit_android_app_summer_2023.model

import com.google.gson.annotations.SerializedName

data class HocKyRespone(
    @SerializedName("data")
    var listHocKyRespone: ListHocKyRespone,
    @SerializedName("result")
    var result: Boolean,
    @SerializedName("code")
    var code: Int
){
    data class ListHocKyRespone(@SerializedName("ds_hoc_ky") var listHocKy: ArrayList<HocKy>){

    }
}