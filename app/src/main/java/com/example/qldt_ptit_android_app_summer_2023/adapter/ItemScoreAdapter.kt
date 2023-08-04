package com.example.qldt_ptit_android_app_summer_2023.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.qldt_ptit_android_app_summer_2023.R
import com.example.qldt_ptit_android_app_summer_2023.model.CreditClass

class ItemScoreAdapter(var context: Context, var dataset: ArrayList<CreditClass>)  : RecyclerView.Adapter<ItemScoreAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var txtSubjectName : TextView
        var txtMidtermScore: TextView
        var txtExamScore: TextView
        var txtFinalScore: TextView
        var txtFinalScore4: TextView
        var txtFinalScoreChar: TextView

        init {
            txtSubjectName = itemView.findViewById(R.id.txt_score_sub_name)
            txtMidtermScore = itemView.findViewById(R.id.txt_midterm_score)
            txtExamScore = itemView.findViewById(R.id.txt_exam_score)
            txtFinalScore = itemView.findViewById(R.id.txt_final_score)
            txtFinalScore4 = itemView.findViewById(R.id.txt_final_score_4)
            txtFinalScoreChar = itemView.findViewById(R.id.txt_final_score_char)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_score, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = dataset.get(position)
        holder.txtSubjectName.text = item.subject.name
        holder.txtMidtermScore.text = item.score!!.midtermScore.toString()
        holder.txtExamScore.text = item.score!!.examScore.toString()
        holder.txtFinalScore.text = item.score!!.finalScore.toString()
        holder.txtFinalScore4.text = item.score!!.finalSoreNum.toString()
        holder.txtFinalScoreChar.text = item.score!!.finalScoreChar.toString()
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}