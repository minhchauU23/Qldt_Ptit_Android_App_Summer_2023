package com.example.qldt_ptit_android_app_summer_2023.adapter

import android.content.Context
import android.service.autofill.Dataset
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.qldt_ptit_android_app_summer_2023.R
import com.example.qldt_ptit_android_app_summer_2023.model.ThoiKhoaBieu
import com.example.qldt_ptit_android_app_summer_2023.model.ToHoc

class ItemTKBAdapter(var context: Context, var dataset: ArrayList<ToHoc>): RecyclerView.Adapter<ItemTKBAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var txtTimeStudy: TextView
        var txtSubjectName: TextView
        var txtLecturerName: TextView
        var txtRoomName: TextView
        init {
            txtTimeStudy = itemView.findViewById(R.id.txt_study_time)
            txtSubjectName = itemView.findViewById(R.id.txt_subject_name)
            txtLecturerName = itemView.findViewById(R.id.txt_lecture_name)
            txtRoomName = itemView.findViewById(R.id.txt_room_name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_tkb, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = dataset.get(position)
        holder.txtTimeStudy.text = item.tkb!!.startClass.startTime
        holder.txtSubjectName.text = item.creditClass!!.subject.name
        holder.txtLecturerName.text = item.lecturer!!.name
        holder.txtRoomName.text = item.room
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}