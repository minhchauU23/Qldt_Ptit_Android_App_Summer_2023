package com.example.qldt_ptit_android_app_summer_2023.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.qldt_ptit_android_app_summer_2023.model.HocKy
import com.example.qldt_ptit_android_app_summer_2023.model.Post
import com.example.qldt_ptit_android_app_summer_2023.model.Student
import com.example.qldt_ptit_android_app_summer_2023.model.User
import java.text.SimpleDateFormat
import kotlin.collections.ArrayList

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


    val tblStudent = "student"
    val studentColumnStudentCode = "student_code"
    val studentColumnGender = "gender"
    val studentColumnDateOfBirth = "dob"
    val studentColumnPlaceOfBirth = "pob"
    val studentColumnNation = "nation"
    val studentColumnReligion = "religion"
    val studentColumnPhone = "phone"
    val studentColumnEmail = "email"
    val studentColumnIdNumber = "idnum"
    val studentColumnClass = "class"
    val studentColumnIndusSector = "indus_sector"
    val studentColumnMajor = "major"
    val studentColumnDepartment = "department"
    val studentColumnTypeOfTraining = "type_of_training"
    val studentColumnSchoolYear = "school_year"
    val studentColumnAdviserCode = "adviser_code"
    val studentColumnAdviserName = "adviser_name"
    val studentColumnInCode = "incode"
    val studentColumnOutCode = "outcode"
    val createTableStudent = "CREATE TABLE IF NOT EXISTS $tblStudent (" +
            " $studentColumnStudentCode TEXT, " +
            " $studentColumnGender TEXT, " +
            " $studentColumnDateOfBirth DATE," +
            " $studentColumnPlaceOfBirth TEXT," +
            " $studentColumnNation TEXT," +
            " $studentColumnReligion TEXT, " +
            " $studentColumnPhone TEXT, " +
            " $studentColumnEmail TEXT, " +
            " $studentColumnIdNumber TEXT, " +
            " $studentColumnClass TEXT," +
            " $studentColumnIndusSector TEXT, " +
            " $studentColumnMajor TEXT, " +
            " $studentColumnDepartment TEXT, " +
            " $studentColumnTypeOfTraining TEXT, " +
            " $studentColumnSchoolYear TEXT," +
            " $studentColumnAdviserCode TEXT, " +
            " $studentColumnAdviserName TEXT, " +
            " $studentColumnInCode INT, " +
            " $studentColumnOutCode INT," +
            " PRIMARY KEY ($studentColumnStudentCode), " +
            " FOREIGN KEY ($studentColumnStudentCode) " +
            " REFERENCES $tblUser($userColumUsername)" +
            " );"

    val tblPost = "post"
    val postColumnID = "id"
    val postColumnTitle = "title"
    val postColumnContent = "content"
    val postColumnPostingDate = "posting_date"
    val postColumnCorrectingDate = "correcting_date"
    val createTablePost = "CREATE TABLE IF NOT EXISTS $tblPost(" +
            " $postColumnID TEXT PRIMARY KEY ," +
            " $postColumnTitle TEXT, " +
            " $postColumnContent TEXT, " +
            " $postColumnPostingDate TEXT, " +
            " $postColumnCorrectingDate TEXT" +
            ");"

    val tblHocKy = "hocky"
    val hocKyColumnID = "id"
    val hocKyColumnDes = "des"
    val hocKyColumnStartDate = "start_date"
    val hockyColumnEndDate = "end_date"
    val createTableHocKy = "CREATE TABLE IF NOT EXISTS $tblHocKy (" +
            " $hocKyColumnID INT PRIMARY KEY," +
            " $hocKyColumnDes TEXT," +
            " $hocKyColumnStartDate DATE," +
            " $hockyColumnEndDate DATE" +
            ");"

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(createTableUser)
        p0?.execSQL(createTableStudent)
        p0?.execSQL(createTablePost)
        p0?.execSQL(createTableHocKy)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    fun insertUser(user: User){
        var query= "INSERT OR REPLACE INTO ${tblUser} VALUES(" +
                " UPPER('${user.username}'), '${user.password}', '${user.fullName}', " +
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

    fun insertStudent(student: Student){
//        var query = "INSERT OR REPLACE INTO $tblStudent VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);"
        var contentValue = ContentValues()
        contentValue.put(studentColumnStudentCode, student.username.uppercase())
        contentValue.put(studentColumnGender, student.gender)
        contentValue.put(studentColumnDateOfBirth, SimpleDateFormat("yyyy-MM-dd").format(student.getDateOfBirth()))
        contentValue.put(studentColumnPlaceOfBirth, student.placeOfBirth)
        contentValue.put(studentColumnNation, student.nation)
        contentValue.put(studentColumnReligion, student.religion)
        contentValue.put(studentColumnPhone, student.phone)
        contentValue.put(studentColumnEmail, student.email)
        contentValue.put(studentColumnIdNumber, student.idNum)
        contentValue.put(studentColumnClass, student.clazz)
        contentValue.put(studentColumnIndusSector, student.indusSector)
        contentValue.put(studentColumnMajor, student.major)
        contentValue.put(studentColumnDepartment, student.department)
        contentValue.put(studentColumnTypeOfTraining, student.typeOfTraining)
        contentValue.put(studentColumnSchoolYear, student.schoolYear)
        contentValue.put(studentColumnAdviserCode, student.adviserCode)
        contentValue.put(studentColumnAdviserName, student.adviserName)
        contentValue.put(studentColumnInCode, student.inCode)
        contentValue.put(studentColumnOutCode, student.outCode)
        writableDatabase.insert(tblStudent,null, contentValue)
        Log.d("insert student", "inserting student with email = ${student.email}")

    }

    fun upsertStudent(student: Student){
        var query = "INSERT OR REPLACE INTO $tblStudent VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);"
        var sqliteStatement = writableDatabase.compileStatement(query)
        sqliteStatement.bindString(1, student.username.uppercase())
        sqliteStatement.bindString(2, student.gender)
        sqliteStatement.bindString(3, SimpleDateFormat("yyyy-MM-dd").format(student.getDateOfBirth()))
        sqliteStatement.bindString(4, student.placeOfBirth)
        sqliteStatement.bindString(5, student.nation)
        sqliteStatement.bindString(6, student.religion)
        sqliteStatement.bindString(7, student.phone)
        sqliteStatement.bindString(8, student.email)
        sqliteStatement.bindString(9, student.idNum)
        sqliteStatement.bindString(9, student.gender)
        sqliteStatement.bindString(10, student.clazz)
        sqliteStatement.bindString(11, student.indusSector)
        sqliteStatement.bindString(12, student.major)
        sqliteStatement.bindString(13, student.department)
        sqliteStatement.bindString(14, student.typeOfTraining)
        sqliteStatement.bindString(15, student.schoolYear)
        sqliteStatement.bindString(16, student.adviserCode)
        sqliteStatement.bindString(17, student.adviserName)
        sqliteStatement.bindLong(18, student.inCode!!.toLong())
        sqliteStatement.bindLong(19, student.outCode!!.toLong())
        sqliteStatement.execute()
        Log.d("upsert student", "inserting student with email = ${student.email}")
    }

    fun getStudent(user: User): Student?{
        var query = "SELECT * FROM $tblStudent WHERE UPPER($studentColumnStudentCode) = UPPER(?);"
        var cursor = writableDatabase.rawQuery(query, arrayOf(user.username))
        Log.d("usname in get", user.username)
        if(cursor.count > 0){
            Log.d("get student", "in if block")
            cursor.moveToFirst()
            var student = Student(user)
            student.gender = cursor.getString(1)
            student.dateOfBirth = SimpleDateFormat("dd/MM/yyyy").format(SimpleDateFormat("yyyy-MM-dd").parse(cursor.getString(2)))
            student.placeOfBirth = cursor.getString(3)
            student.nation = cursor.getString(4)
            student.religion = cursor.getString(5)
            student.phone = cursor.getString(6)
            student.email = cursor.getString(7)
            student.idNum = cursor.getString(8)
            student.clazz = cursor.getString(9)
            student.indusSector = cursor.getString(10)
            student.major = cursor.getString(11)
            student.department = cursor.getString(12)
            student.typeOfTraining = cursor.getString(13)
            student.schoolYear = cursor.getString(14)
            student.adviserCode = cursor.getString(15)
            student.adviserName = cursor.getString(16)
            student.inCode = cursor.getInt(17)
            student.outCode = cursor.getInt(18)
            return student
        }
        else
            return null
    }

    fun upsertPost(post: Post){
        var query = "INSERT OR REPLACE INTO $tblPost VALUES(?, ?, ?, ?, ?);"
        var sqlStatment = writableDatabase.compileStatement(query)
        sqlStatment.bindString(1, post.id)
        sqlStatment.bindString(2, post.title)
        sqlStatment.bindString(3, post.content)
        sqlStatment.bindString(4, post.postingDate)
        sqlStatment.bindString(5, post.correctionDate)
        sqlStatment.execute()
        Log.d("upsert post", "upserting post with title = ${post.title}")
    }

    fun getPosts(): ArrayList<Post>?{
        var listPosts = arrayListOf<Post>()
        var query = "SELECT * FROM $tblPost;"
        var cursor = writableDatabase.rawQuery(query, null)
        while(cursor.moveToNext()){
            listPosts.add(Post(cursor.getString(0),cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4) ))
        }
        return listPosts
    }

    fun upsertHocKy(hocky: HocKy){
        val query = "INSERT OR REPLACE INTO $tblHocKy VALUES(${hocky.id}, '${hocky.description}', '${SimpleDateFormat("yyyy-MM-dd").format(hocky.getStartDate())}', '${SimpleDateFormat("yyyy-MM-dd").format(hocky.getEndDate())}');"
        writableDatabase.execSQL(query)
        Log.d("hocky", "upserting hoc ky with des = ${hocky.description}")
    }

    fun getAllHocKy(): ArrayList<HocKy>{
        val query = "SELECT * FROM $tblHocKy order by $hockyColumnEndDate desc;"
        var cursor = writableDatabase.rawQuery(query, null)
        var listHocKy = arrayListOf<HocKy>()
        while (cursor.moveToNext()){
            listHocKy.add(HocKy(cursor.getInt(0), cursor.getString(1),
                SimpleDateFormat("dd/MM/yyyy").format(SimpleDateFormat("yyyy-MM-dd").parse( cursor.getString(2))),
                SimpleDateFormat("dd/MM/yyyy").format(SimpleDateFormat("yyyy-MM-dd").parse( cursor.getString(3)))
                ))
        }
        return listHocKy
    }
}