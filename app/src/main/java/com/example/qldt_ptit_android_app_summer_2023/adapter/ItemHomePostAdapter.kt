package com.example.qldt_ptit_android_app_summer_2023.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.qldt_ptit_android_app_summer_2023.R
import com.example.qldt_ptit_android_app_summer_2023.fragment.HomeFragment
import com.example.qldt_ptit_android_app_summer_2023.model.Post

class ItemHomePostAdapter(var context: Context, var dataset: ArrayList<Post>, var onItemClickListener: HomeFragment.ItemClickListener): RecyclerView.Adapter<ItemHomePostAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var txtTitle: TextView
        init {
            txtTitle = itemView.findViewById(R.id.txt_title_post)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_post_fragment_home, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = dataset.get(position)
        holder.txtTitle.text = item.title
        holder.itemView.setOnClickListener {
            onItemClickListener.setOnItemClick(item)
        }
        Log.d("txt_title", item.title)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }


}