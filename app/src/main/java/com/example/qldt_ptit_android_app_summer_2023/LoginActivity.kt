package com.example.qldt_ptit_android_app_summer_2023

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.qldt_ptit_android_app_summer_2023.api.QldtService
import com.example.qldt_ptit_android_app_summer_2023.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class LoginActivity : AppCompatActivity() {
    lateinit var edtUsername: EditText
    lateinit var edtPassword: EditText
    lateinit var btnLogin: Button
    var retrofit = QldtService.retrofit
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        edtUsername = findViewById(R.id.edt_username)
        edtPassword = findViewById(R.id.edt_password)
        btnLogin = findViewById(R.id.btn_login)
        btnLogin.setOnClickListener { view:View ->
            var username = edtUsername.text.toString()
            var password = edtPassword.text.toString()
            var user = User(username, password)
            if(user.isValidUsername() && user.isValidPassword())
                login(user)
            else Toast.makeText(applicationContext, "Invalid username or password", Toast.LENGTH_LONG)
        }
    }

    fun login(user: User){
        retrofit.login(user.username, user.password).enqueue(object : Callback<User>{
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if(response.code() == 200){
                    var body = response.body()
                    user.fullName = body?.fullName
                    user.roles = body?.roles!!
                    user.accessToken = body?.accessToken!!
                    user.tokenType = body?.tokenType!!
                }
                else {
                    Toast.makeText(applicationContext, response.message(), Toast.LENGTH_LONG)
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {

            }
        })


    }

}