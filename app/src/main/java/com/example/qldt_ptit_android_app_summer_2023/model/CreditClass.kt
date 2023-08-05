package com.example.qldt_ptit_android_app_summer_2023.model

data class CreditClass( var code: String, var group: String, var hocKy: HocKy, var subject: Subject) {

    var score: Score? = null
}