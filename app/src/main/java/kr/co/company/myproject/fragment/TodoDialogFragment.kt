package kr.co.company.myproject.fragment

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
import kotlinx.android.synthetic.main.todo_dialog.*
import kr.co.company.myproject.R
import kr.co.company.myproject.domain.todo.Todo
import kr.co.company.myproject.viewModel.TodoViewModel
import java.time.LocalDateTime

class TodoDialogFragment : Fragment() {
    lateinit var navController : NavController
    lateinit var todo: Todo
    private val todoViewModel : TodoViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val navHostFragment =  parentFragmentManager.findFragmentById(R.id.home) as NavHostFragment
//        val navController = navHostFragment.navController
        val args : TodoDialogFragmentArgs by navArgs()
        todo = args.todo

        todo.startDate=todo.startDate?: LocalDateTime.now()
        todo.endDate=todo.endDate?: LocalDateTime.now()
//        this.binding = TodoDialogBinding.inflate(layoutInflater)
//        setContentView(binding.root)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.todo_dialog,container,false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController= Navigation.findNavController(view)
        start_datePicker.init(todo.startDate?.year?:LocalDateTime.now().year,
            todo.startDate?.monthValue?.minus(1) ?: LocalDateTime.now().monthValue-1,
            todo.startDate?.dayOfMonth?:LocalDateTime.now().dayOfMonth
        ) { view, y, m,d->
            todo.startDate = LocalDateTime.of(y,m+1,d,0,0)
//            todo.startYear = y
//            todo.startMonth = m+1
//            todo.startDay = d
        }
        end_datePicker.init(todo.endDate?.year?:LocalDateTime.now().year,
            todo.endDate?.monthValue?.minus(1) ?: LocalDateTime.now().monthValue-1,
            todo.endDate?.dayOfMonth?:LocalDateTime.now().dayOfMonth) { view, y, m, d ->
            todo.endDate = LocalDateTime.of(y,m+1,d,0,0)
//            todo.endYear = y
//            todo.endMonth = m+1
//            todo.endDay = d
        }
        title_edit.setText(todo.name?:"")
        memo_edit.setText(todo.memo?:"")
        if(todo.name.isNullOrBlank()){
            delete_button.visibility=View.GONE
        }
        cancel_button.setOnClickListener {
            val action =
                TodoDialogFragmentDirections.actionTodoDialogFragmentToMainFragment()
            navController.navigate(action)
//            dialog.dismiss()
        }
        ok_button.setOnClickListener {
            if(TextUtils.isEmpty(title_edit.text.toString())){
                Toast.makeText(requireContext(),"제목을 입력해주세요", Toast.LENGTH_SHORT,).show()
            }
            else{
                todo.name = title_edit.text.toString()
                todo.memo = memo_edit.text.toString()
                todoViewModel.addTodo(todo)
                val action =
                    TodoDialogFragmentDirections.actionTodoDialogFragmentToMainFragment()
                navController.navigate(action)
            //                NavHostFragment.findNavController(this).navigate(action)
//                findNavController().navigate(action)
            }
        }
        this.delete_button.setOnClickListener{
            todoViewModel.deleteTodo(todo)
            val action =
                TodoDialogFragmentDirections.actionTodoDialogFragmentToMainFragment()
            navController.navigate(action)
        }
    }
}