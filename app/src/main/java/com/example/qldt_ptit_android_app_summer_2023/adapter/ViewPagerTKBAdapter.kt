package com.example.qldt_ptit_android_app_summer_2023.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ViewPagerTKBAdapter(fragmentManager: FragmentManager): FragmentStatePagerAdapter(fragmentManager) {
    private var fragmentDataset = ArrayList<Fragment>()
    private var fragmentTitle = ArrayList<String>()

    override fun getCount(): Int {
        return fragmentDataset.size
    }

    override fun getItem(position: Int): Fragment {
        return fragmentDataset.get(position)

    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentTitle.get(position)
    }

    fun addFragment(frag: Fragment, title : String){
        fragmentDataset.add(frag)
        fragmentTitle.add(title)
    }
}