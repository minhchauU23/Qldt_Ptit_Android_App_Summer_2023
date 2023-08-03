package com.example.qldt_ptit_android_app_summer_2023

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.qldt_ptit_android_app_summer_2023.adapter.ViewPagerMainAdapter
import com.example.qldt_ptit_android_app_summer_2023.model.Student
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var bottomNavBar: BottomNavigationView
    lateinit var viewPager: ViewPager
    lateinit var viewPagerAdapter: ViewPagerMainAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavBar = findViewById(R.id.bottom_nav_bar)
        viewPager = findViewById(R.id.view_pager_main)
        var student = intent.getSerializableExtra("student")
        viewPagerAdapter = ViewPagerMainAdapter(supportFragmentManager, student as Student)
        viewPager.adapter = viewPagerAdapter

    }
}