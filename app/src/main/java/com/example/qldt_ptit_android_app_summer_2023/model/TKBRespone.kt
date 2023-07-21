package com.example.qldt_ptit_android_app_summer_2023.model

import com.google.gson.annotations.SerializedName

data class TKBRespone(
    @SerializedName("data")
    var dataRespone: DataRespone,
    @SerializedName("result")
    var result: Boolean,
    @SerializedName("code")
    var code: Int
) {


    data class DataRespone(
        @SerializedName("ds_tiet_trong_ngay")
        var listTiet : ArrayList<Tiet>,
        @SerializedName("ds_tuan_tkb")
        var listTuan: ArrayList<TuanRespone>
    ){

    }

    data class TuanRespone(
        @SerializedName("tuan_hoc_ky")
        var tuanHocKy: Int,
        @SerializedName("tuan_tuyet_doi")
        var tuanTuyetDoi: Int,
        @SerializedName("thong_tin_tuan")
        var description: String,
        @SerializedName("ngay_bat_dau")
        var startDate: String,
        @SerializedName("ngay_ket_thuc")
        var endDate: String,
        @SerializedName("ds_thoi_khoa_bieu")
        var listTkb: ArrayList<RawTKB>
    ){

    }

    data class RawTKB(
        @SerializedName("thu_kieu_so")
        var thu: Int,
        @SerializedName("tiet_bat_dau")
        var tietBD: Int,
        @SerializedName("so_tiet")
        var soTiet: Int,
        @SerializedName("ma_mon")
        var maMon: String,
        @SerializedName("ten_mon")
        var tenMon: String,
        @SerializedName("so_tin_chi")
        var sotc: String,
        @SerializedName("id_to_hoc")
        var idToHoc: String,
        @SerializedName("id_tkb")
        var idTKB: String,
        @SerializedName("ma_nhom")
        var maNhom: String,
        @SerializedName("ma_giang_vien")
        var maGiangVien: String,
        @SerializedName("ten_giang_vien")
        var tenGiangVien: String,
        @SerializedName("ma_lop")
        var maLop: String,
        @SerializedName("ma_phong")
        var maPhong: String
    ){
    }
}



