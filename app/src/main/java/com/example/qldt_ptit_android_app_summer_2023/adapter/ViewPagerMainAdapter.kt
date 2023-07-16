package com.example.qldt_ptit_android_app_summer_2023.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.qldt_ptit_android_app_summer_2023.fragment.HomeFragment

class ViewPagerMainAdapter(fragmentManager: FragmentManager, ): FragmentStatePagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return 1
    }

    override fun getItem(position: Int): Fragment {
            return HomeFragment()        
    }
}