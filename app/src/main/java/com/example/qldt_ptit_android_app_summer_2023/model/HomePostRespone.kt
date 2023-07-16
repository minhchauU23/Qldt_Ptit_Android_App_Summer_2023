package com.example.qldt_ptit_android_app_summer_2023.model

import com.google.gson.annotations.SerializedName

data class HomePostRespone(@SerializedName("data") var listPostRespone: ListPostRepone, var result: Boolean, var code: Int ) {
    data class ListPostRepone(@SerializedName("ds_bai_viet") var lsPost: ArrayList<Post>){

    }
}