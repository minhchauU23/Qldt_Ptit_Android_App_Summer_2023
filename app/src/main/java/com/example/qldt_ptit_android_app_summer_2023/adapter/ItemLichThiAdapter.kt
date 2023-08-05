package com.example.qldt_ptit_android_app_summer_2023.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.qldt_ptit_android_app_summer_2023.R
import com.example.qldt_ptit_android_app_summer_2023.model.LichThi

class ItemLichThiAdapter(var context: Context, var dataset: ArrayList<LichThi>) : RecyclerView.Adapter<ItemLichThiAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var txtSubjectName: TextView
        var txtExamDate: TextView
        var txtStartExam: TextView
        var txtMinutes: TextView
        var txtRoom: TextView

        init {
            txtSubjectName = itemView.findViewById(R.id.txt_subject_name_lich_thi)
            txtExamDate = itemView.findViewById(R.id.txt_examdate)
            txtStartExam = itemView.findViewById(R.id.txt_start_exam)
            txtMinutes = itemView.findViewById(R.id.txt_minutes)
            txtRoom = itemView.findViewById(R.id.txt_exam_room)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_lich_thi, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = dataset.get(position)
        holder.txtSubjectName.text = item.subject!!.name
        holder.txtExamDate.text = item.date
        holder.txtStartExam.text = item.startTime
        holder.txtMinutes.text = item.minutes
        holder.txtRoom.text = item.room
    }

    override fun getItemCount(): Int {
        return dataset.size
    }


}