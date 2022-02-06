package kr.co.company.myproject.domain.category

import android.os.Build
import android.os.Parcelable
import androidx.annotation.RequiresApi
import androidx.room.*
import kotlinx.android.parcel.Parcelize
import kr.co.company.myproject.domain.LocalDateConverter
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Entity
@Parcelize
class Category (
    @TypeConverters(LocalDateConverter::class)
    @ColumnInfo(name="category_start_date")
    var startDate : LocalDate,
    @TypeConverters(LocalDateConverter::class)
    @ColumnInfo(name="category_end_date")
    var endDate :LocalDate
    ):Parcelable{

    constructor(startDate: LocalDate, endDate: LocalDate, name:String,memo:String):
            this(startDate,endDate){
        this.name=name
        this.memo=memo
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="category_id")
    var id : Long = 0L
    @ColumnInfo(name="category_name")
    var name : String? = null
//    var startYear : Int = 0
//    var startMonth: Int = 0
//    var startDay : Int = 0
//    var endYear : Int = 0
//    var endMonth : Int = 0
//    var endDay : Int = 0
@ColumnInfo(name="category_memo")
    var memo : String? = null
    @TypeConverters(LocalDateConverter::class)
    @RequiresApi(Build.VERSION_CODES.O)
    fun period():String{
        val startformat = startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        val endformat = endDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        return "$startformat ~ $endformat"
    }
}