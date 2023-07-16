package com.example.qldt_ptit_android_app_summer_2023

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.qldt_ptit_android_app_summer_2023.model.Post

class DetailPostActivity : AppCompatActivity() {
    lateinit var txtDetailTitlePost: TextView
    lateinit var txtDetatilContentPost:  TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_post)
        txtDetailTitlePost = findViewById(R.id.txt_title_detail_post)
        txtDetatilContentPost = findViewById(R.id.txt_content_detail_post)
        var post = intent.getSerializableExtra("post") as Post
        txtDetailTitlePost.text = post.title
        txtDetatilContentPost.text = post.convertContentFromHtml()
    }
}