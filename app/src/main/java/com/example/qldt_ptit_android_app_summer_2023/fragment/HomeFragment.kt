package com.example.qldt_ptit_android_app_summer_2023.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.qldt_ptit_android_app_summer_2023.R
import com.example.qldt_ptit_android_app_summer_2023.adapter.ItemHomePostAdapter
import com.example.qldt_ptit_android_app_summer_2023.database.QldtHelper
import com.example.qldt_ptit_android_app_summer_2023.DetailPostActivity
import com.example.qldt_ptit_android_app_summer_2023.model.Post

class HomeFragment: Fragment() {
    lateinit var rcvHome: RecyclerView
    lateinit var itemHomePostAdapter: ItemHomePostAdapter
    var listPost: ArrayList<Post>? = ArrayList()
    var dbHelper = QldtHelper.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_home, container, false)
        rcvHome = view.findViewById(R.id.rcv_home)
        getPosts()
        itemHomePostAdapter = ItemHomePostAdapter(requireContext(), listPost!!, object : ItemClickListener{
            override fun setOnItemClick(item: Post) {
                var intent = Intent(requireContext(), DetailPostActivity::class.java)
                intent.putExtra("post", item)
                startActivity(intent)
            }
        })
        Log.d("lspost", listPost.toString())
        rcvHome.adapter = itemHomePostAdapter
        return view
    }

    fun getPosts(){
        listPost = dbHelper.getPosts()
    }


    interface ItemClickListener{
        fun setOnItemClick(item: Post)
    }


}