package kr.co.company.myproject.domain.todo

import android.os.Build
import android.os.Parcelable
import androidx.annotation.RequiresApi
import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import kotlinx.android.parcel.Parcelize
import kr.co.company.myproject.domain.LocalDateConverter
import kr.co.company.myproject.domain.category.Category
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Entity(foreignKeys = arrayOf(ForeignKey(onDelete = CASCADE,
entity = Category::class,
parentColumns = arrayOf("category_id"),
childColumns = arrayOf("category_id"))))
@Parcelize
class Todo (
    @ColumnInfo(name="category_id")
    var categoryId:Long,
    @TypeConverters(LocalDateConverter::class)
    @ColumnInfo(name="todo_start_date")
    var startDate : LocalDate,
    @TypeConverters(LocalDateConverter::class)
    @ColumnInfo(name="todo_end_date")
    var endDate :LocalDate
    ):Parcelable{

    constructor(categoryId:Long, startDate: LocalDate, endDate: LocalDate, name:String,memo:String)
            : this(categoryId,startDate,endDate) {
        this.name=name
        this.memo=memo
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="todo_id")
    var id : Long = 0L
    @ColumnInfo(name="todo_name")
    var name : String? = null
//    var startYear : Int = 0
//    var startMonth: Int = 0
//    var startDay : Int = 0
//    var endYear : Int = 0
//    var endMonth : Int = 0
//    var endDay : Int = 0
    @ColumnInfo(name="todo_memo")
    var memo : String? = null
    @ColumnInfo(name="todo_checked")
    var checked:Boolean = false

    @RequiresApi(Build.VERSION_CODES.O)
    fun period():String{
        val startformat = startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        val endformat = endDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        return "$startformat ~ $endformat"
    }
}