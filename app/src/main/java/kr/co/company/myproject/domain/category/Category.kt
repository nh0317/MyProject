package kr.co.company.myproject.domain.category

import android.os.Build
import android.os.Parcelable
import androidx.annotation.RequiresApi
import androidx.room.*
import kotlinx.android.parcel.Parcelize
import kr.co.company.myproject.domain.LocalDateTimeConverter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Entity
@Parcelize
class Category ():Parcelable{
//    constructor(sy: Int, sm: Int, sd: Int, ey: Int, em: Int, ed: Int, name: String, memo: String):this (){
//        this.startYear=sy
//        this.startMonth=sm
//        this.startDay=sd
//        this.endYear=ey
//        this.endMonth=em
//        this.endDay=ed
//        this.name=name
//        this.memo=memo
//    }
//
//    constructor(sy: Int, sm: Int, sd: Int, ey: Int, em: Int, ed: Int):this (){
//        this.startYear=sy
//        this.startMonth=sm
//        this.startDay=sd
//        this.endYear=ey
//        this.endMonth=em
//        this.endDay=ed
//    }
    constructor(startDateTime: LocalDateTime,endDateTime: LocalDateTime) : this() {
        this.startDate=startDateTime
        this.endDate=endDateTime
    }
    constructor(name:String,memo:String):this(){
        this.name=name
        this.memo=memo
    }

    @PrimaryKey(autoGenerate = true)
    var id : Long = 0L
    var name : String? = null
//    var startYear : Int = 0
//    var startMonth: Int = 0
//    var startDay : Int = 0
//    var endYear : Int = 0
//    var endMonth : Int = 0
//    var endDay : Int = 0
    var memo : String? = null
    @TypeConverters(LocalDateTimeConverter::class)
    @ColumnInfo(name="start_date")
    var startDate :LocalDateTime? = null
    @TypeConverters(LocalDateTimeConverter::class)
    @ColumnInfo(name="end_date")
    var endDate :LocalDateTime? = null
    @RequiresApi(Build.VERSION_CODES.O)
    fun period():String{
        val startformat = startDate?.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        val endformat = endDate?.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        return "$startformat ~ $endformat"
    }

}