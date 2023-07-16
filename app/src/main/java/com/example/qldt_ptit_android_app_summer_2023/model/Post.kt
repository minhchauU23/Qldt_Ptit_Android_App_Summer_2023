package com.example.qldt_ptit_android_app_summer_2023.model

import com.google.gson.annotations.SerializedName
import org.jsoup.Jsoup

data class Post(
    @SerializedName("id") var id: String,
    @SerializedName("tieu_de") var title: String,
    @SerializedName("noi_dung") var content: String,
    @SerializedName("ngay_dang_tin") var postingDate: String,
    @SerializedName("ngay_hieu_chinh") var correctionDate: String) : java.io.Serializable{

    fun convertContentFromHtml(): String{
        var jsoup = Jsoup.parse(content)
        var contentConverted = ""
        var pTags = jsoup.getElementsByTag("p")
        for(p in pTags){
            contentConverted += p.text()
            contentConverted += "\n"
        }
        return contentConverted
    }
}