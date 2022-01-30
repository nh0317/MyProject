package kr.co.company.myproject.domain.todo

import android.os.Build
import android.os.Parcelable
import androidx.annotation.RequiresApi
import androidx.room.*
import kotlinx.android.parcel.Parcelize
import kr.co.company.myproject.domain.LocalDateTimeConverter
import kr.co.company.myproject.domain.category.Category
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Entity(foreignKeys = arrayOf(ForeignKey(
entity = Category::class,
parentColumns = arrayOf("id"),
childColumns = arrayOf("category_id"))))
@Parcelize
class Todo (
    @ColumnInfo(name="category_id")
    var category_id:Long):Parcelable{
    constructor(categoryId:Long, startDateTime: LocalDateTime,endDateTime: LocalDateTime) : this(categoryId){
        this.startDate=startDateTime
        this.endDate=endDateTime
    }
    constructor(categoryId:Long, name:String,memo:String) : this(categoryId) {
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
    var checked:Boolean = false
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