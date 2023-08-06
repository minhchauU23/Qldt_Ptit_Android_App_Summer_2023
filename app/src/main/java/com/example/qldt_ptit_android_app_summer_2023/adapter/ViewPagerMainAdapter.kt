package com.example.qldt_ptit_android_app_summer_2023.adapter

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.qldt_ptit_android_app_summer_2023.fragment.*
import com.example.qldt_ptit_android_app_summer_2023.model.LichThi
import com.example.qldt_ptit_android_app_summer_2023.model.Score
import com.example.qldt_ptit_android_app_summer_2023.model.Student

class ViewPagerMainAdapter(fragmentManager: FragmentManager,var student: Student ): FragmentStatePagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return 5
    }

    override fun getItem(position: Int): Fragment {
        when(position){
            0->{return HomeFragment()}
            1->{return TkbFragment(student) }
            2->{return ScoreFragment(student)}
            3->{return LichThiFragment(student)}
            4->{return InforUserFragment(student)}
            else->{return ScoreFragment(student)}
//            else->return Fragment()
        }
    }
}