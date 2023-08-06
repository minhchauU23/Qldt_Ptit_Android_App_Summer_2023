package com.example.qldt_ptit_android_app_summer_2023.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.qldt_ptit_android_app_summer_2023.R
import com.example.qldt_ptit_android_app_summer_2023.model.Student

class InforUserFragment(var student: Student):Fragment() {
    lateinit var avt: ImageView
    lateinit var txtStudentCode: TextView
    lateinit var txtFullName: TextView
    lateinit var txtGender: TextView
    lateinit var txtPlaceOfBirth: TextView
    lateinit var txtEmail: TextView
    lateinit var txtClasss: TextView
    lateinit var txtMajorStudyName: TextView
    lateinit var txtStudyYear: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_infor_user, container, false)
        avt = view.findViewById(R.id.avt)
        txtStudentCode = view.findViewById(R.id.txt_user_name)
        txtFullName = view.findViewById(R.id.txt_full_name)
        txtGender = view.findViewById(R.id.txt_gender)
        txtPlaceOfBirth = view.findViewById(R.id.txt_place_of_birth)
        txtEmail = view.findViewById(R.id.txt_email)
        txtClasss = view.findViewById(R.id.txt_class)
        txtMajorStudyName = view.findViewById(R.id.txt_major)
        txtStudyYear = view.findViewById(R.id.txt_study_year)

        if(student.avt != null){
            avt.setImageURI(student.avt)
        }
        txtStudentCode.text = student.username
        txtFullName.text = student.fullName
        txtGender.text = student.gender
        txtPlaceOfBirth.text = student.placeOfBirth
        txtEmail.text = student.email
        txtClasss.text = student.clazz
        txtMajorStudyName.text = student.major
//        txtStudyYear.text = "${(student.inCode!!.div(10))} - ${(student.outCode!!.div(10))}"

        avt.setOnClickListener(object : OnClickListener{
            override fun onClick(p0: View?) {
                var intent = Intent()
                intent.setType("image/*")
                intent.setAction(Intent.ACTION_GET_CONTENT)
                startActivityForResult(intent, 111)
            }

        })
        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.d("resultcode", resultCode.toString())
        if(resultCode == -1){
            if(requestCode == 111){
                var uri = data!!.data
                if(uri != null){
                    student.avt = uri
                    avt.setImageURI(uri)
                }

            }
        }
    }

}