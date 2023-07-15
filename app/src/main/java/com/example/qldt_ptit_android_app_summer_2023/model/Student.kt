package com.example.qldt_ptit_android_app_summer_2023.model

import com.google.gson.annotations.SerializedName
import java.util.Date

class Student(username: String, password: String) : User(username, password) {
    @SerializedName("gioi_tinh")
    var gender: String? = null
    @SerializedName("ngay_sinh")
    var dateOfBirth: Date? = null
    @SerializedName("noi_sinh")
    var placeOfBirth: String? = null
    @SerializedName("dan_toc")
    var nation: String? = null
    @SerializedName("ton_giao")
    var religion: String? = null
    @SerializedName("dien_thoai")
    var phone: String? = null
    var email: String? = null
    @SerializedName("so_cmnd")
    var idNum: String? = null
    @SerializedName("lop")
    var clazz: String? = null
    @SerializedName("khoi")
    var indusSector: String? = null
    @SerializedName("nganh")
    var major: String? = null
    @SerializedName("khoa")
    var department: String? = null
    @SerializedName("bac_he_dao_tao")
    var typeOfTraining: String? = null
    @SerializedName("nien_khoa")
    var schoolYear: String? = null
    @SerializedName("ma_cvht")
    var adviserCode: String? = null
    @SerializedName("ho_ten_cvht")
    var adviserName: String? = null
    @SerializedName("nhhk_vao")
    var inCode: Int? = null
    @SerializedName("nhhk_ra")
    var outCode: Int? = null
}