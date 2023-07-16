package com.example.qldt_ptit_android_app_summer_2023.model

import android.icu.text.CaseMap.Title
import android.util.Log
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.Date

data class Post(
    @SerializedName("id") var id: String,
    @SerializedName("tieu_de") var title: String,
    @SerializedName("noi_dung") var content: String,
    @SerializedName("ngay_dang_tin") var postingDate: String,
    @SerializedName("ngay_hieu_chinh") var correctionDate: String) {


}