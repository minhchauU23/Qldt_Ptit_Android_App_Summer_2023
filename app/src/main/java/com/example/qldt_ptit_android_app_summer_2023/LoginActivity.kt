package com.example.qldt_ptit_android_app_summer_2023

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.qldt_ptit_android_app_summer_2023.api.QldtService
import com.example.qldt_ptit_android_app_summer_2023.database.QldtHelper
import com.example.qldt_ptit_android_app_summer_2023.model.FilterRequest
import com.example.qldt_ptit_android_app_summer_2023.model.HocKy
import com.example.qldt_ptit_android_app_summer_2023.model.User
import com.google.gson.Gson
import kotlinx.coroutines.*
import retrofit2.Response
import retrofit2.Retrofit

class LoginActivity : AppCompatActivity() {
    lateinit var edtUsername: EditText
    lateinit var edtPassword: EditText
    lateinit var btnLogin: Button
    var retrofit = QldtService.retrofit

    lateinit var dbHelper: QldtHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        edtUsername = findViewById(R.id.edt_username)
        edtPassword = findViewById(R.id.edt_password)
        btnLogin = findViewById(R.id.btn_login)
        QldtHelper.context = applicationContext
        dbHelper = QldtHelper.getInstance()
        btnLogin.setOnClickListener { view:View ->
            var username = edtUsername.text.toString()
            var password = edtPassword.text.toString()
            var user = User(username, password)
            if(user.isValidUsername() && user.isValidPassword()){
                if(isConnectInternet())
                    callApi(user)
                else loginWithoutInternet(user)

            }
            else Toast.makeText(applicationContext, "Invalid username or password", Toast.LENGTH_LONG)
        }
    }

    fun isConnectInternet() : Boolean{
        var connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if(connectivityManager != null){
            var capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if(capabilities != null){
                if(capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || capabilities.hasTransport(
                        NetworkCapabilities.TRANSPORT_ETHERNET) || capabilities.hasTransport(
                        NetworkCapabilities.TRANSPORT_WIFI)){
                    return true
                }
            }
        }
        return false
    }

    fun loginWithoutInternet(user: User){
        if(dbHelper.getUser(user)){
            var intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        else{
            Toast.makeText(applicationContext, "Invalid username or password", Toast.LENGTH_LONG).show()
        }
    }

    fun callApi(user: User){
        var cou = GlobalScope.launch(Dispatchers.IO) {
            var loginJob = launch {
                var respone = retrofit.login(user.username, user.password)
                Log.d("res code", respone.code().toString())
                if(respone.code() == 200){
                    var body = respone.body()
                    user.fullName = body?.fullName
                    user.roles = body?.roles!!
                    user.accessToken = body?.accessToken!!
                    user.tokenType = body?.tokenType!!
                    Log.d("user", user.roles)
                }
                else{
                    withContext(Dispatchers.Main){
                        Toast.makeText(applicationContext, "Invalid username or password", Toast.LENGTH_LONG).show()
                    }
                }
            }
            var insertUserJob = launch {
                loginJob.join()
                if(user.isInitialized()){
                    dbHelper.insertUser(user)
                }
            }
            var getHomePostJob = launch {
                loginJob.join()
                var filter = FilterRequest()
                filter.addFilter("ky_hieu", "tb")
                filter.addFilter("is_hien_thi", true)
                filter.addFilter("is_hinh_dai_dien", false)
                filter.addFilter("is_noi_dung", true)
                filter.addFilter("so_luong_hinh_dai_dien", 0)

                var paging = mutableMapOf<String, Int>()
                paging.put("limit", 10)
                paging.put("page", 1)

                filter.addAdditional("paging", paging)
                var ordering = arrayListOf <Map<String, Any>>()
                var first = mapOf<String, Any>("name" to "do_uu_tien", "order_type" to 1)
                var second = mapOf<String, Any>("name" to "ngay_dang_tin", "order_type" to 1)
                ordering.add(first)
                ordering.add(second)
                filter.addAdditional("ordering", ordering)
                var respone = retrofit.getPostsHome(filter)
                if(respone.code() == 200){
                    var listPost = respone.body()!!.listPostRespone.lsPost
                    for(post in listPost){
                        dbHelper.upsertPost(post)
                    }
                }

            }

            var switchJob = launch {
                loginJob.join()
                if(user.isInitialized()){
                    var intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }

            }

            var getInforJob = launch {
                loginJob.join()
                if(user.isInitialized()){
                    when(user.roles){
                        "SINHVIEN"->{
                            var respone = retrofit.getInfor("${user.tokenType} ${user.accessToken}")
                            var body = respone.body()
                            if(respone.code() == 200){
                                var student = body?.data
                                student?.username = user.username
                                student?.password = user.password
                                student?.fullName = user.fullName
                                student?.roles = user.roles
                                student?.accessToken = user.accessToken
                                student?.tokenType = user.tokenType
                                dbHelper.upsertStudent(student!!)
                            }
                        }
                    }
                }
            }

            var listHocKyRespone: ArrayList<HocKy> = ArrayList()
            var getHocKyJob = launch {
                loginJob.join()
                if(user.isInitialized()){
                    var filter = FilterRequest()
                    filter.addFilter("is_tieng_anh", null)
                    filter.addAdditional("paging", mapOf("limit" to 100, "page" to 1))
                    var ordering = mapOf<String, Any>("name" to "hoc_ky", "order_type" to 1)
                    filter.addAdditional("ordering", arrayOf(ordering))
                    var respone = retrofit.getHocKy("${user.tokenType} ${user.accessToken}", filter)
                    if(respone.code() == 200){
                        Log.d("headerhk", respone.headers().toString())
                        Log.d("hocky", respone.body().toString())
                        listHocKyRespone = respone.body()!!.listHocKyRespone.listHocKy
                    }
                }
            }

            val insertHocKyJob = launch {
                getHocKyJob.join()
                if(listHocKyRespone.size > 0){
                    for(hocky in listHocKyRespone){
                        dbHelper.upsertHocKy(hocky)
                    }
                }
            }

            val getTKBJob = launch {
                getHocKyJob.join()
                if(listHocKyRespone.size > 0){
                    for(hocky in listHocKyRespone){

                        var filter = FilterRequest()
                        filter.addFilter("hoc_ky", hocky.id)
                        filter.addFilter("ten_hoc_ky" , "")
                        filter.addAdditional("paging", mapOf("limit" to 100, "page" to 1))
                        var ordering = mapOf<String, Any?>("name" to null, "order_type" to null)
                        filter.addAdditional("ordering", arrayListOf(ordering))
                        var respone = retrofit.getTKB("${user.tokenType} ${user.accessToken}", filter)
                        if(respone.code() == 200){
                            var lsTiet = respone.body()!!.dataRespone.listTiet
                            for(tiet in lsTiet){
                                dbHelper.upsertTiet(tiet)
                            }

                        }

                    }
                }
            }


        }
    }

}