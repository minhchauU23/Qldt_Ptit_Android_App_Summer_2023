package com.example.qldt_ptit_android_app_summer_2023.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.qldt_ptit_android_app_summer_2023.model.User

class QldtHelper: SQLiteOpenHelper{
    companion object{
        var context: Context? = null
        var name: String = "QLDT"
        var version = 1
        private var helper: QldtHelper? = null
        fun getInstance(): QldtHelper{
            if(helper == null){
                helper = QldtHelper(context!!)
            }
            return helper!!
        }
    }
    private constructor(context: Context):
            super(context, name, null, version)

    val tblUser = "user"
    val userColumUsername = "username"
    val userColumnPassword = "password"
    val userColumnFullname = "name"
    val userColumnRole = "roles"
    val userColumnAccessToken = "access_token"
    val userColumnTokenType = "token_type"
    val createTableUser = "CREATE TABLE IF NOT EXISTS ${tblUser}(" +
            "${userColumUsername} TEXT PRIMARY KEY, " +
            "${userColumnPassword} TEXT, " +
            "${userColumnFullname} TEXT, " +
            "${userColumnRole} TEXT, " +
            "${userColumnAccessToken} TEXT, " +
            "${userColumnTokenType} TEXT );"


    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(createTableUser)

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    fun insertUser(user: User){
        var query= "INSERT OR REPLACE INTO ${tblUser} VALUES(" +
                " '${user.username}', '${user.password}', '${user.fullName}', " +
                " '${user.roles}', '${user.accessToken}', '${user.tokenType}');"
        writableDatabase.execSQL(query)
    }

    fun getUser(user: User): Boolean{
        var query = "SELECT * FROM ${tblUser} WHERE UPPER(${userColumUsername}) = UPPER('${user.username}') AND ${userColumnPassword} = '${user.password}'; "
        var cur = writableDatabase.rawQuery(query, null)
        if(cur.moveToNext()){
            user.fullName = cur.getString(2)
            user.roles = cur.getString(3)
            user.accessToken = cur.getString(4)
            user.tokenType = cur.getString(5)
            return true
        }
        return false
    }

}