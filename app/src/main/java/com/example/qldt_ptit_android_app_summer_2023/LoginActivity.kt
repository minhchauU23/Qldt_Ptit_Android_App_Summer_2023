package com.example.qldt_ptit_android_app_summer_2023

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.qldt_ptit_android_app_summer_2023.api.QldtService
import com.example.qldt_ptit_android_app_summer_2023.database.QldtHelper
import com.example.qldt_ptit_android_app_summer_2023.model.Student
import com.example.qldt_ptit_android_app_summer_2023.model.User
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
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
                if(user.isInitialized()){
                    var intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            else Toast.makeText(applicationContext, "Invalid username or password", Toast.LENGTH_LONG)
        }
    }

    fun isConnectInternet() : Boolean{
        return true
    }

    fun loginWithoutInternet(user: User){
        dbHelper.getUser(user)
    }

    fun callApi(user: User){
        GlobalScope.launch(Dispatchers.IO) {
            var loginJob = launch {
                var respone = retrofit.login(user.username, user.password)
                Log.d("res code", respone.code().toString())
                if(respone.code() == 200){
                    var body = respone.body()
                    user.fullName = body?.fullName
                    user.roles = body?.roles!!
                    user.accessToken = body?.accessToken!!
                    user.tokenType = body?.tokenType!!
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
//                    Log.d("insert", "inserting user with name = ${user.fullName}")
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
                                dbHelper.insertStudent(student!!)
//                                var studentTestGet = dbHelper.getStudent(user)
//                                Log.d("get student", studentTestGet?.dateOfBirth!!)
                            }
                        }
                    }
                }
            }


        }
    }

}