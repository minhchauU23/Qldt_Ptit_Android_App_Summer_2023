package com.example.qldt_ptit_android_app_summer_2023.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.qldt_ptit_android_app_summer_2023.R
import com.example.qldt_ptit_android_app_summer_2023.adapter.ItemScoreAdapter
import com.example.qldt_ptit_android_app_summer_2023.database.QldtHelper
import com.example.qldt_ptit_android_app_summer_2023.model.Student

class ScoreFragment(var student: Student) : Fragment(){
    lateinit var rcvScore: RecyclerView
    lateinit var itemScoreAdapter: ItemScoreAdapter
    var dbHelper = QldtHelper.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_score, container, false)
        rcvScore = view.findViewById(R.id.rcv_score)
        var dataset = dbHelper.getCreditClassWithScore(student.username)
        itemScoreAdapter = ItemScoreAdapter(requireContext(), dataset)
        rcvScore.adapter = itemScoreAdapter
        itemScoreAdapter.notifyDataSetChanged()
        return view
    }


}