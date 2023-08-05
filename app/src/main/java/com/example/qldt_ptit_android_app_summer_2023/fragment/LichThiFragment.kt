package com.example.qldt_ptit_android_app_summer_2023.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.qldt_ptit_android_app_summer_2023.R
import com.example.qldt_ptit_android_app_summer_2023.adapter.ItemLichThiAdapter
import com.example.qldt_ptit_android_app_summer_2023.database.QldtHelper
import com.example.qldt_ptit_android_app_summer_2023.model.HocKy
import com.example.qldt_ptit_android_app_summer_2023.model.LichThi
import com.example.qldt_ptit_android_app_summer_2023.model.Student

class LichThiFragment(var student: Student) : Fragment(){
    lateinit var spnHocKy: Spinner
    lateinit var rcvLichThi: RecyclerView
    lateinit var hocKyAdapter: ArrayAdapter<HocKy>
    lateinit var itemLichThiAdapter: ItemLichThiAdapter
    var hocKyDataset = ArrayList<HocKy>()
    var lichThiDataset = ArrayList<LichThi>()
    var dbHelper = QldtHelper.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_lich_thi, container, false)
        spnHocKy = view.findViewById(R.id.spn_hk_lich_thi)
        rcvLichThi = view.findViewById(R.id.rcv_lich_thi)
        loadHocKy()
        loadLichThi(hocKyDataset.get(0))
        return view
    }

    fun loadHocKy(){
        hocKyDataset = dbHelper.getAllHocKy()
        hocKyAdapter = ArrayAdapter(requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, hocKyDataset)
        spnHocKy.adapter = hocKyAdapter
        spnHocKy.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                var itemHocKy = hocKyDataset.get(p2)
                loadLichThi(itemHocKy)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }
    }

    fun loadLichThi(hocKy: HocKy){
        lichThiDataset = dbHelper.getLichThiByHocKy(hocKy, student)
        for(lt in lichThiDataset){
            Log.d("lt", lt.subject!!.name)
        }
        itemLichThiAdapter = ItemLichThiAdapter(requireContext(), lichThiDataset)
        rcvLichThi.adapter = itemLichThiAdapter
    }

}