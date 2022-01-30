package kr.co.company.myproject.fragment

import android.app.Dialog
import android.content.Context
import android.os.Build
import android.text.TextUtils
import android.view.WindowManager
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import kr.co.company.myproject.R
import kr.co.company.myproject.domain.category.Category
import java.time.LocalDateTime

class CategoryDialog(context : Context, private val category: Category){
    private val dialog = Dialog(context)
    private lateinit var onClickListener : OnDialogClickListener

    @RequiresApi(Build.VERSION_CODES.O)
    fun showDialog() {
        dialog.setContentView(R.layout.category_dialog)
        dialog.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
        dialog.setCanceledOnTouchOutside(false)
        dialog.setCancelable(true)
        dialog.show()

        val startDatePicker = dialog.findViewById<DatePicker>(R.id.start_datePicker)
        val endDatePicker = dialog.findViewById<DatePicker>(R.id.end_datePicker)
        val editTitle = dialog.findViewById<EditText>(R.id.title_edit)
        val editMemo = dialog.findViewById<EditText>(R.id.memo_edit)
        val cancelButton: Button = dialog.findViewById<Button>(R.id.cancel_button)
        val okButton: Button = dialog.findViewById(R.id.ok_button)
//        val year = category.year
//        val month = today.monthValue
//        val day = today.dayOfMonth
//        val category = Category(year,month,day,year,month,day)
        startDatePicker.init(category.startDate!!.year,category.startDate!!.monthValue-1,category.startDate!!.dayOfMonth
        ) { view, y, m,d->
            category.startDate = LocalDateTime.of(y,m+1,d,0,0)
//            category.startYear = y
//            category.startMonth = m+1
//            category.startDay = d
        }
        endDatePicker.init(category.endDate!!.year,category.endDate!!.monthValue-1,category.endDate!!.dayOfMonth
        ) { view, y, m, d ->
            category.startDate = LocalDateTime.of(y,m+1,d,0,0)
//            category.endYear = y
//            category.endMonth = m+1
//            category.endDay = d
        }
        editTitle.setText(category.name?:"")
        editMemo.setText(category.memo?:"")
        cancelButton.setOnClickListener {
            dialog.dismiss()
        }
        okButton.setOnClickListener {
            if(TextUtils.isEmpty(editTitle.text.toString())){
                Toast.makeText(dialog.context,"제목을 입력해주세요",Toast.LENGTH_SHORT,).show()
            }
            else{
                category.name = editTitle.text.toString()
                category.memo = editMemo.text.toString()
                onClickListener.onClicked(category)
                dialog.dismiss()
            }
        }
    }

    fun setOnClickListener(listener: OnDialogClickListener) {
        this.onClickListener = listener
    }

    interface OnDialogClickListener{
        fun onClicked(category: Category)
    }

}