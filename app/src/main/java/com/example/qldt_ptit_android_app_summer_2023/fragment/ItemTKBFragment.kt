package com.example.qldt_ptit_android_app_summer_2023.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.qldt_ptit_android_app_summer_2023.R
import com.example.qldt_ptit_android_app_summer_2023.adapter.ItemTKBAdapter
import com.example.qldt_ptit_android_app_summer_2023.model.ThoiKhoaBieu
import com.example.qldt_ptit_android_app_summer_2023.model.ToHoc

class ItemTKBFragment(var dataset: ArrayList<ToHoc>): Fragment() {
    lateinit var rcvItemTKB: RecyclerView
    lateinit var itemTKBAdapter: ItemTKBAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = layoutInflater.inflate(R.layout.fragment_item_tkb, container, false)
        rcvItemTKB = view.findViewById(R.id.rcv_item_tkb)
        itemTKBAdapter = ItemTKBAdapter(requireContext(), dataset)
        rcvItemTKB.adapter = itemTKBAdapter
        return view
    }
}