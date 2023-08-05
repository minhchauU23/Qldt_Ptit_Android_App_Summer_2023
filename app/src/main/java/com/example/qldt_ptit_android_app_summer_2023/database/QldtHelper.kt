package com.example.qldt_ptit_android_app_summer_2023.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.qldt_ptit_android_app_summer_2023.model.*
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

    val tblTiet = "Tiet"
    val tietColumnTiet = "tiet"
    val tietColumnStartTime = "start_time"
    val tietColumnEndTime = "end_time"
    val tietColumnHocKyID = "hk_id"
    val createTableTiet = "CREATE TABLE IF NOT EXISTS $tblTiet(" +
            " $tietColumnTiet INT," +
            " $tietColumnStartTime TEXT," +
            " $tietColumnEndTime TEXT," +
            " $tietColumnHocKyID INT," +
            " PRIMARY KEY ($tietColumnTiet, $tietColumnHocKyID)," +
            " FOREIGN KEY ($tietColumnHocKyID) REFERENCES $tblHocKy($hocKyColumnID)" +
            ");"

    val tblTuan = "tuan"
    val tuanColumnTuanHocKy = "tuan_hoc_ky"
    val tuanColumnTuanTuyetDoi = "tuan_tuyet_doi"
    val tuanColumnChiTiet = "description"
    val tuanColumnStartDate = "start_date"
    val tuanColumnEndDate = "end_date"
    val tuanColumnHocKyID = "hkid"
    val creatTableTuan = "CREATE TABLE IF NOT EXISTS $tblTuan(" +
            " $tuanColumnTuanHocKy INT," +
            " $tuanColumnTuanTuyetDoi INT," +
            " $tuanColumnChiTiet TEXT," +
            " $tuanColumnStartDate TEXT," +
            " $tuanColumnEndDate TEXT," +
            " $tuanColumnHocKyID INT," +
            " PRIMARY KEY($tuanColumnTuanHocKy, $tuanColumnHocKyID)," +
            " FOREIGN KEY($tuanColumnHocKyID) " +
            " REFERENCES $tblHocKy($hocKyColumnID)" +
            ");"

    val tblSubject = "subject"
    val subjectColumnID = "id"
    val subjectColumnName = "name"
    val subjectColumnNumOfCredit = "credits"
    val createTableSubject = "CREATE TABLE IF NOT EXISTS $tblSubject(" +
            " $subjectColumnID TEXT PRIMARY KEY," +
            " $subjectColumnName TEXT," +
            " $subjectColumnNumOfCredit TEXT" +
            ");"

    val tblLecturer = "lecturer"
    val lecturerColumnID = "id"
    val lecturerColumnName = "name"
    val createTableLecturer = "CREATE TABLE IF NOT EXISTS $tblLecturer(" +
            " $lecturerColumnID TEXT PRIMARY KEY," +
            " $lecturerColumnName TEXT" +
            ");"

    val tblCreditClass = "credit_class"
    val creditClasssColumnCode = "code"
    val creditClasssColumnGroup = "group_study"
    val creditClasssColumnHocKyID = "hkid"
    val creditClasssColumnSubjectID = "subject_id"
    val createTableCreditClasssQldt = "CREATE TABLE IF NOT EXISTS $tblCreditClass(" +
            " $creditClasssColumnCode TEXT ," +
            " $creditClasssColumnGroup TEXT," +
            " $creditClasssColumnHocKyID INT," +
            " $creditClasssColumnSubjectID TEXT," +
            " PRIMARY KEY ($creditClasssColumnCode, $creditClasssColumnSubjectID)," +
            " FOREIGN KEY ($creditClasssColumnHocKyID) " +
            "   REFERENCES $tblHocKy($hocKyColumnID)," +
            " FOREIGN KEY ($creditClasssColumnSubjectID)" +
            "   REFERENCES $tblSubject($subjectColumnID)" +
            ");"

    val tblScore = "student_class"
    val scoreColumnStudentID = "mssv"
    val scoreColumnCreditClassID = "credit_class_id"
    val scoreColumnSubjectID = "subject_id"
    val scoreColumnExamScore = "exam"
    val scoreColumnMidtermScore = "midterm"
    val scoreColumnFinalScore = "final"
    val scoreColumnFinalScoreNum = "final4th"
    val scoreColumnFinalScoreChar = "final_char"
    val createTableScore = "CREATE TABLE IF NOT EXISTS $tblScore(" +
            " $scoreColumnStudentID TEXT," +
            " $scoreColumnCreditClassID TEXT," +
            " $scoreColumnSubjectID TEXT," +
            " $scoreColumnExamScore REAL," +
            " $scoreColumnMidtermScore REAL," +
            " $scoreColumnFinalScore REAL," +
            " $scoreColumnFinalScoreNum REAL," +
            " $scoreColumnFinalScoreChar TEXT," +
            " PRIMARY KEY($scoreColumnStudentID, $scoreColumnCreditClassID, $scoreColumnSubjectID), " +
            " FOREIGN KEY ($scoreColumnStudentID) " +
            "    REFERENCES $tblStudent($studentColumnStudentCode)," +
            " FOREIGN KEY ($scoreColumnCreditClassID, $scoreColumnSubjectID) " +
            "    REFERENCES $tblCreditClass($creditClasssColumnCode, $creditClasssColumnSubjectID)" +
            ");"

    val tblTKB = "tkb"
    val tkbColumnID = "id"
    val tkbColumnDate = "date"
    val tkbColumnClassStart = "class_start"
    val tkbColumnNumberOfLessons = "num_of_lesson"
    val tkbColumnWeekID = "week_id"
    val tkbColumnHocKyID = "hkid"
    val createTableTKB = "CREATE TABLE IF NOT EXISTS $tblTKB(" +
            " $tkbColumnID TEXT ," +
            " $tkbColumnDate INT," +
            " $tkbColumnClassStart INT," +
            " $tkbColumnNumberOfLessons INT," +
            " $tkbColumnHocKyID INT," +
            " $tkbColumnWeekID INT," +
            " PRIMARY KEY ($tkbColumnID, $tkbColumnWeekID)," +
            " FOREIGN KEY ($tkbColumnWeekID)" +
            "   REFERENCES $tblTuan($tuanColumnTuanTuyetDoi)," +
            " FOREIGN KEY ($tkbColumnClassStart, $tkbColumnHocKyID) " +
            "   REFERENCES $tblTiet($tietColumnTiet, $tietColumnHocKyID)" +
            ");"
    

    val tblToHoc = "to_hoc"
    val toHocColumnID = "id"
    val toHocColumnCreditClass = "credit_class_id"
    val toHocColumnRoom = "room"
    val toHocColumnLecturerID = "lecture_id"
    val createTableToHoc = "CREATE TABLE IF NOT EXISTS $tblToHoc(" +
            " $toHocColumnID TEXT PRIMARY KEY," +
            " $toHocColumnCreditClass TEXT," +
            " $toHocColumnRoom TEXT," +
            " $toHocColumnLecturerID TEXT," +
            " FOREIGN KEY ($toHocColumnLecturerID)" +
            "   REFERENCES $tblLecturer($lecturerColumnID)" +
            ");"

    val tblToHocTKB = "tkb_to_hoc"
    val toHocTKBColumnToHocID = "to_hoc_id"
    val toHocTKBColumnTKBID = "tkb_id"
    val createTableToHocTKB = "CREATE TABLE IF NOT EXISTS $tblToHocTKB (" +
            " $toHocTKBColumnToHocID TEXT," +
            " $toHocTKBColumnTKBID TEXT," +
            " PRIMARY KEY ($toHocTKBColumnToHocID, $toHocTKBColumnTKBID)," +
            " FOREIGN KEY ($toHocTKBColumnToHocID)" +
            "   REFERENCES $tblToHoc($toHocColumnID)," +
            " FOREIGN KEY ($toHocTKBColumnTKBID)" +
            "   REFERENCES $tblTKB($tkbColumnID)" +
            ");"


    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(createTableUser)
        p0?.execSQL(createTableStudent)
        p0?.execSQL(createTablePost)
        p0?.execSQL(createTableHocKy)
        p0?.execSQL(createTableTiet)
        p0?.execSQL(creatTableTuan)
        p0?.execSQL(createTableSubject)
        p0?.execSQL(createTableLecturer)
        p0?.execSQL(createTableCreditClasssQldt)
        p0?.execSQL(createTableTKB)
        p0?.execSQL(createTableToHoc)
        p0?.execSQL(createTableToHocTKB)
        p0?.execSQL(createTableScore)
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
    }

    fun getHocKyByID(id : Int): HocKy?{
        val query = "SELECT * FROM $tblHocKy WHERE $hocKyColumnID = $id;"
        var cursor = writableDatabase.rawQuery(query, null)
        if(cursor.count > 0){
            cursor.moveToFirst()
            return HocKy(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3))
        }
        return null
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

    fun upsertTiet(tiet: Tiet){
        var query = "INSERT OR REPLACE INTO $tblTiet VALUES(?, ?, ?, ?);"
        var sqliteStatement = writableDatabase.compileStatement(query)
        sqliteStatement.bindLong(1, tiet.tiet!!.toLong())
        sqliteStatement.bindString(2, tiet.startTime)
        if(tiet.endTime == null){
            tiet.endTime = "";
        }
        sqliteStatement.bindString(3, tiet.endTime)
        sqliteStatement.bindLong(4, tiet.hkid!!.toLong())
        sqliteStatement.execute()
    }

    fun upsertTuan(tuan: Tuan){
        var query = "INSERT OR REPLACE INTO $tblTuan VALUES(?, ?, ?, ?, ?, ?);"
        var sqliteStatement  = writableDatabase.compileStatement(query)
        sqliteStatement.bindLong(1, tuan.tuanHocKy.toLong())
        sqliteStatement.bindLong(2, tuan.tuanTuyetDoi.toLong())
        sqliteStatement.bindString(3, tuan.description)
        sqliteStatement.bindString(4, tuan.startDate)
        sqliteStatement.bindString(5, tuan.endDate)
        sqliteStatement.bindLong(6, tuan.hocKy.id.toLong())
        sqliteStatement.execute()
    }

    fun getTuanByHocKy(hocky: HocKy): ArrayList<Tuan>{
        var query = "SELECT * FROM $tblTuan WHERE $tuanColumnHocKyID = ? ORDER BY $tuanColumnTuanTuyetDoi;"
        var cursor = writableDatabase.rawQuery(query, arrayOf(hocky.id.toString()) )
        var listWeeks = arrayListOf<Tuan>()
        while (cursor.moveToNext()){
            listWeeks.add(Tuan(cursor.getInt(0), cursor.getInt(1), cursor.getString(2),
                cursor.getString(3), cursor.getString(4), hocky))
        }
        return listWeeks
    }

    fun upsertSubject(subject: Subject){
        val query = "INSERT OR REPLACE INTO $tblSubject VALUES(?, ?, ?);"
        Log.d("sub", subject.subId + " " + subject.name)
        val sqlStatement = writableDatabase.compileStatement(query)
        sqlStatement.bindString(1, subject.subId)
        sqlStatement.bindString(2, subject.name)
        sqlStatement.bindString(3, subject.numOfCredit.toString())
        sqlStatement.execute()
    }

    fun getSubjectByID(id: String): Subject?{
        var query = "SELECT * FROM $tblSubject WHERE id = ?;"
        var cursor = writableDatabase.rawQuery(query, arrayOf(id))
        if(cursor.count > 0){
            return Subject(cursor.getString(0), cursor.getString(1), cursor.getFloat(2))
        }
        return null
    }

    fun upsertLecturer(lecturer: Lecturer){
        val query = "INSERT OR REPLACE INTO $tblLecturer VALUES(?,?);"
        val sqlStatement = writableDatabase.compileStatement(query)
        sqlStatement.bindString(1, lecturer.lecturerCode)
        sqlStatement.bindString(2, lecturer.name)
        sqlStatement.execute()
    }

    fun getLecturerById(id: String): Lecturer?{
        var query = "SELECT * FROM $tblLecturer WHERE id = ?;"
        var cursor = writableDatabase.rawQuery(query, arrayOf(id))
        if(cursor.count > 0){
            return Lecturer(cursor.getString(0), cursor.getString(1))
        }
        return null
    }


    fun upsertCreditClass(creditClass: CreditClass){
        var query = "INSERT OR REPLACE INTO $tblCreditClass VALUES(?, ?, ?, ?);"
        Log.d("cred", "${creditClass.code}  ${creditClass.subject.subId}  ${creditClass.subject.name}")
        var sqlStatement = writableDatabase.compileStatement(query)
        sqlStatement.bindString(1, creditClass.code)
        sqlStatement.bindString(2, creditClass.group)
        sqlStatement.bindLong(3, creditClass.hocKy.id.toLong())
        sqlStatement.bindString(4, creditClass.subject.subId)
        sqlStatement.execute()
    }

    fun getCreditClassID(hkid: Int, subjectID: String, group: String): String{
        var query = "SELECT $creditClasssColumnCode FROM $tblCreditClass " +
                " WHERE $creditClasssColumnHocKyID = $hkid AND UPPER($creditClasssColumnSubjectID) = UPPER('$subjectID') AND $creditClasssColumnGroup = '${group}' ;"
//        Log.d("query", query)
        var cur = writableDatabase.rawQuery(query, null)
//        Log.d("cur", cur.count.toString())
        if(cur.count > 0){
            cur.moveToFirst()
            return cur.getString(0)
        }
        return ""
    }


    fun getCreditClassWithScore(studentID: String): ArrayList<CreditClass>{
        var dataset = ArrayList<CreditClass>()
        var query = "SELECT a.$scoreColumnExamScore, a.$scoreColumnMidtermScore, a.$scoreColumnFinalScore, a.$scoreColumnFinalScoreNum, a.$scoreColumnFinalScoreChar, b.$creditClasssColumnCode, b.$creditClasssColumnGroup, b.$creditClasssColumnHocKyID, b.$creditClasssColumnSubjectID, c.$subjectColumnName, c.$subjectColumnNumOfCredit  " +
                "   FROM $tblScore a INNER JOIN $tblCreditClass b INNER JOIN $tblSubject c" +
                "   ON UPPER(a.$scoreColumnStudentID) = UPPER('$studentID') AND a.$scoreColumnCreditClassID = b.$creditClasssColumnCode AND a.$scoreColumnSubjectID = b.$creditClasssColumnSubjectID " +
                "   AND b.$creditClasssColumnSubjectID = c.$subjectColumnID  ORDER BY  b.$creditClasssColumnHocKyID;"


        var cursor = writableDatabase.rawQuery(query, null)

        while (cursor.moveToNext()){

            var score = Score(cursor.getFloat(0), cursor.getFloat(1), cursor.getFloat(2), cursor.getFloat(3), cursor.getString(4))
            var hocky = getHocKyByID(cursor.getInt(7))
            var subject = Subject(cursor.getString(8), cursor.getString(9), cursor.getFloat(10))
            var creditClass = CreditClass(cursor.getString(5), cursor.getString(6), hocky!!, subject)
            Log.d("subname", subject.name)
            creditClass.score = score
            dataset.add(creditClass)
        }
        return dataset
    }

    fun upsertToHoc(tohoc: ToHoc){
        var query = "INSERT OR REPLACE INTO $tblToHoc VALUES(?, ?, ?, ?);"
        var sqlStatement = writableDatabase.compileStatement(query)

        sqlStatement.bindString(1, tohoc.id)
        sqlStatement.bindString(2, tohoc.creditClass?.code)
        sqlStatement.bindString(3, tohoc.room)
        sqlStatement.bindString(4, tohoc.lecturer?.lecturerCode)
        sqlStatement.execute()
    }

    fun insertScore(creditClass: CreditClass, student: User){
        var query = "INSERT OR REPLACE INTO $tblScore ($scoreColumnStudentID, $scoreColumnCreditClassID, $scoreColumnSubjectID) " +
                "   VALUES(UPPER(?), ?, ?);"
        var sqlStatement = writableDatabase.compileStatement(query)
        sqlStatement.bindString(1, student.username)
        sqlStatement.bindString(2, creditClass.code)
        sqlStatement.bindString(3, creditClass.subject.subId)
        sqlStatement.execute()
    }

    fun updateScore(userId: String, creditClassID: String, subjectID: String,score: Score){
        var query = "UPDATE $tblScore SET " +
                "   $scoreColumnExamScore = ${score.examScore} ," +
                "   $scoreColumnMidtermScore = ${score.midtermScore}," +
                "   $scoreColumnFinalScore = ${score.finalScore}," +
                "   $scoreColumnFinalScoreNum = ${score.finalSoreNum}," +
                "   $scoreColumnFinalScoreChar = '${score.finalScoreChar}' " +
                " WHERE UPPER($scoreColumnStudentID) = UPPER('$userId') AND UPPER($scoreColumnCreditClassID) = UPPER('$creditClassID') AND UPPER($scoreColumnSubjectID) = UPPER('$subjectID');"
        writableDatabase.execSQL(query)
    }

    fun upsertTKB(tkb: ThoiKhoaBieu){
        var query = "INSERT OR REPLACE INTO $tblTKB VALUES(?, ?, ?, ?, ?, ?);"
        var sqlStatement = writableDatabase.compileStatement(query)
        sqlStatement.bindString(1, tkb.id)
        sqlStatement.bindLong(2, tkb.date.toLong())
        sqlStatement.bindLong(3, tkb.startClass.tiet!!.toLong())
        sqlStatement.bindLong(4, tkb.numOfLesson.toLong())
        sqlStatement.bindLong(5, tkb.tuan.hocKy.id.toLong() )
        sqlStatement.bindLong(6, tkb.tuan.tuanTuyetDoi.toLong())
        sqlStatement.execute()
    }

    fun getTKB(student: Student, tuan: Tuan, thu: Int): ArrayList<ToHoc>{
        var dataset = ArrayList<ToHoc>()
        var query = "SELECT b.$creditClasssColumnCode, b.$creditClasssColumnGroup, c.$subjectColumnID, c.$subjectColumnName, c.$subjectColumnNumOfCredit, d.$toHocColumnRoom, e.$lecturerColumnID, e.$lecturerColumnName, f.$tkbColumnID, f.$tkbColumnDate, f.$tkbColumnNumberOfLessons, g.$tietColumnTiet, g.$tietColumnStartTime, g.$tietColumnEndTime  " +
                "   FROM  $tblScore a INNER JOIN $tblCreditClass b INNER JOIN $tblSubject c INNER JOIN $tblToHoc d INNER JOIN  $tblLecturer e INNER JOIN $tblToHocTKB dtof INNER JOIN $tblTKB f INNER JOIN $tblTiet g" +
                "   ON UPPER(a.$scoreColumnStudentID) = UPPER('${student.username}') AND a.$scoreColumnCreditClassID = b.$creditClasssColumnCode" +
                "       AND b.$creditClasssColumnSubjectID = c.$subjectColumnID AND d.$toHocColumnCreditClass = b.$creditClasssColumnCode " +
                "       AND e.$lecturerColumnID = d.$toHocColumnLecturerID AND  d.$toHocColumnID = dtof.$toHocTKBColumnToHocID AND dtof.$toHocTKBColumnTKBID = f.$tkbColumnID AND f.$tkbColumnWeekID = ${tuan.tuanTuyetDoi} AND f.$tkbColumnDate = $thu AND f.$tkbColumnClassStart = g.$tietColumnTiet AND g.$tietColumnHocKyID = ${tuan.hocKy.id} ORDER BY g.$tietColumnTiet; "
        var cursor = readableDatabase.rawQuery(query, null)
        while (cursor.moveToNext()){
            var subject = Subject(cursor.getString(2), cursor.getString(3), cursor.getFloat(4))
            var creditClass = CreditClass(cursor.getString(0), cursor.getString(1), tuan.hocKy, subject)
            var lecturer = Lecturer(cursor.getString(6), cursor.getString(7))
            var toHoc = ToHoc()
            toHoc.room = cursor.getString(5)
            toHoc.creditClass = creditClass
            toHoc.lecturer = lecturer
            var tiet = Tiet()
            tiet.tiet = cursor.getInt(11)
            tiet.startTime = cursor.getString(12)
            tiet.endTime = cursor.getString(13)
            var tkb = ThoiKhoaBieu(cursor.getString(8), cursor.getInt(9), tiet,cursor.getInt(10), tuan )
            toHoc.tkb = tkb
            dataset.add(toHoc)
        }
        return dataset
    }

    fun upsertToHocTKB(tohoc: ToHoc, tkb: ThoiKhoaBieu){
        var query = "INSERT OR REPLACE INTO $tblToHocTKB VALUES(?, ?);"
        var sqlStatement = writableDatabase.compileStatement(query)
        sqlStatement.bindString(1, tohoc.id)
        sqlStatement.bindString(2, tkb.id)
        sqlStatement.execute()
    }


    fun testGet(){
        var query = "SELECT * FROM $tblScore ;"
        var cursor = writableDatabase.rawQuery(query, null)
        while(cursor.moveToNext()){
            for(it in 0 until  cursor.columnCount){
                var x: String? = cursor.getString(it)
                Log.d("cs", x.toString()?:"null")
            }
        }
    }

}