package kr.co.company.myproject.fragment

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.category_dialog.*
import kr.co.company.myproject.R
import kr.co.company.myproject.domain.category.Category
import kr.co.company.myproject.viewModel.CategoryViewModel
import java.time.LocalDate
import java.time.LocalDateTime

class CategoryDialogFragment : Fragment() {
    lateinit var navController : NavController
    lateinit var category: Category
    private var tabPosition = 0
    private val categoryViewModel : CategoryViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pref = context?.getSharedPreferences("viewInfo", Context.MODE_PRIVATE)
        tabPosition = pref?.getInt("tabPosition",0) ?: 0
//        val navHostFragment =  parentFragmentManager.findFragmentById(R.id.home) as NavHostFragment
//        val navController = navHostFragment.navController
        val args : CategoryDialogFragmentArgs by navArgs()
        category = args.category

        Log.i("CategoryDialogFragment",category.startDate.toString())
        Log.i("CategoryDialogFragment",category.endDate.toString())
        Log.i("CategoryDialogFragment",category.period())
//        this.binding = CategoryDialogBinding.inflate(layoutInflater)
//        setContentView(binding.root)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.category_dialog,container,false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController= Navigation.findNavController(view)
        start_datePicker.init(category.startDate.year,category.startDate.monthValue-1,category.startDate.dayOfMonth
        ) { view, y, m,d->
            category.startDate = LocalDate.of(y,m+1,d)
//            category.startYear = y
//            category.startMonth = m+1
//            category.startDay = d
        }
        end_datePicker.init(category.endDate.year,category.endDate.monthValue-1,category.endDate.dayOfMonth
        ) { view, y, m, d ->
            category.endDate = LocalDate.of(y,m+1,d)
//            category.endYear = y
//            category.endMonth = m+1
//            category.endDay = d
        }
        title_edit.setText(category.name?:"")
        memo_edit.setText(category.memo?:"")
        if(category.name.isNullOrBlank()){
            delete_button.visibility=View.GONE
        }
        cancel_button.setOnClickListener {
            val action =
                CategoryDialogFragmentDirections.actionCategoryDialogFragment2ToMainFragment().setTabPosition(tabPosition)
            navController.navigate(action)
//            dialog.dismiss()
        }
        ok_button.setOnClickListener {
            if(TextUtils.isEmpty(title_edit.text.toString())){
                Toast.makeText(requireContext(),"????????? ??????????????????", Toast.LENGTH_SHORT,).show()
            }
            else if(category.endDate!! < category.startDate)
                Toast.makeText(requireContext(),"??????????????? ???????????? ??? ???????????????", Toast.LENGTH_SHORT,).show()
            else{
                category.name = title_edit.text.toString()
                category.memo = memo_edit.text.toString()
                categoryViewModel.addCategory(category)
                val action =
                    CategoryDialogFragmentDirections.actionCategoryDialogFragment2ToMainFragment().setTabPosition(tabPosition)
                navController.navigate(action)
            //                NavHostFragment.findNavController(this).navigate(action)
//                findNavController().navigate(action)
            }
        }
        this.delete_button.setOnClickListener{
            Log.i("CategoryDialogFragment","tab positiion $tabPosition")
            categoryViewModel.deleteCategory(category)
            val action =
                CategoryDialogFragmentDirections.actionCategoryDialogFragment2ToMainFragment().setTabPosition(tabPosition)
            navController.navigate(action)
        }
    }
}