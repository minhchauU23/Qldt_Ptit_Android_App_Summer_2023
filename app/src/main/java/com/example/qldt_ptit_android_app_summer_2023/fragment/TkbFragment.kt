package com.example.qldt_ptit_android_app_summer_2023.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.qldt_ptit_android_app_summer_2023.R
import com.example.qldt_ptit_android_app_summer_2023.adapter.ViewPagerTKBAdapter
import com.example.qldt_ptit_android_app_summer_2023.database.QldtHelper
import com.example.qldt_ptit_android_app_summer_2023.model.*
import com.google.android.material.tabs.TabLayout


class TkbFragment(var student: Student) : Fragment() {
    lateinit var spnHocKy : Spinner
    lateinit var spnTuan: Spinner
    lateinit var tabLayout: TabLayout
    lateinit var viewPagerTKB: ViewPager
    lateinit var viewPagerTKBAdapter: ViewPagerTKBAdapter
    lateinit var itemHocKyAdapter: ArrayAdapter<HocKy>
    lateinit var hkDataset: ArrayList<HocKy>
    lateinit var itemWeekAdapter: ArrayAdapter<Tuan>
    lateinit var weekDataset: ArrayList<Tuan>
    lateinit var toHocDataset: ArrayList<ToHoc>
    var dbHelper = QldtHelper.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_tkb, container, false)
        spnHocKy = view.findViewById(R.id.spn_hk)
        spnTuan = view.findViewById(R.id.spn_week)
        tabLayout = view.findViewById(R.id.tab_layout)
        viewPagerTKB = view.findViewById(R.id.tkb_viewpager)

        loadHocKy()
        itemHocKyAdapter = ArrayAdapter(requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, hkDataset)
        loadWeek(hkDataset.get(0))
        spnHocKy.adapter = itemHocKyAdapter
        if(weekDataset.size > 0)
            loadListTKB(weekDataset.get(0))
        viewPagerTKB.adapter = viewPagerTKBAdapter

        spnHocKy.onItemSelectedListener = object : OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Log.d("Selection 0", "Selection 0")
                var item = hkDataset.get(p2)
                loadWeek(item)
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
        spnTuan.onItemSelectedListener = object : OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                var item = weekDataset.get(p2)
                loadListTKB(item)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }
        return view
    }

    fun loadHocKy(){
        hkDataset = dbHelper.getAllHocKy()
    }

    fun loadWeek(hocKy: HocKy){
        weekDataset = dbHelper.getTuanByHocKy(hocKy)
        itemWeekAdapter = ArrayAdapter(requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, weekDataset)
        spnTuan.adapter = itemWeekAdapter
    }


    fun loadListTKB(tuan: Tuan){
        viewPagerTKBAdapter = ViewPagerTKBAdapter(childFragmentManager)
        toHocDataset = ArrayList()
        for(date in 2..7){
            toHocDataset = dbHelper.getTKB(student, tuan,date)
            var itemTkb = ItemTKBFragment(toHocDataset)
            viewPagerTKBAdapter.addFragment(itemTkb, "Thứ $date")
            Log.d("tkb", toHocDataset.toString())
        }
        viewPagerTKB.adapter = viewPagerTKBAdapter
        tabLayout.setupWithViewPager(viewPagerTKB)
    }
}