package com.example.qldt_ptit_android_app_summer_2023

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.core.view.get
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.example.qldt_ptit_android_app_summer_2023.adapter.ViewPagerMainAdapter
import com.example.qldt_ptit_android_app_summer_2023.model.Student
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener

class MainActivity : AppCompatActivity() {
    lateinit var bottomNavBar: BottomNavigationView
    lateinit var viewPager: ViewPager
    lateinit var viewPagerAdapter: ViewPagerMainAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavBar = findViewById(R.id.bottom_nav_bar)
        viewPager = findViewById(R.id.view_pager_main)
        var student = intent.getSerializableExtra("student") as Student?
        viewPagerAdapter = ViewPagerMainAdapter(supportFragmentManager, student!! )
        viewPager.adapter = viewPagerAdapter
        bottomNavBar.setOnItemSelectedListener(object : OnItemSelectedListener{
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when(item.itemId){
                    R.id.mHome -> {viewPager.setCurrentItem(0, true)}
                    R.id.mTkb -> {viewPager.setCurrentItem(1, true)}
                    R.id.mDiem -> {viewPager.setCurrentItem(2, true)}
                    R.id.mLichThi -> {viewPager.setCurrentItem(3, true)}
                    R.id.mInfor ->{viewPager.setCurrentItem(4, true)}
                }
                return true
            }

        })

        viewPager.addOnPageChangeListener(object : OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int,
            ) {
            }

            override fun onPageSelected(position: Int) {
                when(position){
                    0->{bottomNavBar.menu.findItem(R.id.mHome).setChecked(true)}
                    1->{bottomNavBar.menu.findItem(R.id.mTkb).setChecked(true)}
                    2->{bottomNavBar.menu.findItem(R.id.mDiem).setChecked(true)}
                    3->{bottomNavBar.menu.findItem(R.id.mLichThi).setChecked(true)}
                    4->{bottomNavBar.menu.findItem(R.id.mInfor).setChecked(true)}
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })
    }
}