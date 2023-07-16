package com.example.qldt_ptit_android_app_summer_2023

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.qldt_ptit_android_app_summer_2023.adapter.ViewPagerMainAdapter
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
        viewPagerAdapter = ViewPagerMainAdapter(supportFragmentManager)
        viewPager.adapter = viewPagerAdapter
    }
}