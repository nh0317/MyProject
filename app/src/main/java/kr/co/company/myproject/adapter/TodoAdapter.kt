package kr.co.company.myproject.adapter

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.todo_item_layout.view.*
import kr.co.company.myproject.databinding.TodoItemLayoutBinding
import kr.co.company.myproject.domain.category.Category
import kr.co.company.myproject.domain.todo.Todo
import kr.co.company.myproject.fragment.MainFragmentDirections
import kr.co.company.myproject.viewModel.CategoryViewModel
import kr.co.company.myproject.viewModel.TodoViewModel
import java.time.LocalDateTime
import java.util.Collections.emptyList

class TodoAdapter(private val context: Context,
                  private val todoViewModel: TodoViewModel,
                  private val categoryViewModel: CategoryViewModel,
                  private val category: Category)
    : RecyclerView.Adapter<TodoAdapter.MyViewHolder>() {
      private var todoList = emptyList<Todo>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = TodoItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        binding.checkbox.setOnCheckedChangeListener(null)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(todoList[position],category,todoViewModel, categoryViewModel)
    }

    fun setData(it: List<Todo>) {
        this.todoList=it
        notifyDataSetChanged()
    }

//    fun setTodos(it:List<Todo>){
//        this.todoList=it
//        notifyDataSetChanged()
//    }

    fun getTodoId(position: Int): Long {
        return position.toLong()
    }
    fun getTodoId():Int{
        return getTodoId().toInt()
    }

    inner class MyViewHolder(private val binding: TodoItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        lateinit var navController: NavController
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(todo : Todo,  category: Category, todoViewModel: TodoViewModel, categoryViewModel: CategoryViewModel){
            binding.todo=todo
            if(todo.checked) {
                itemView.visibility = View.GONE
                itemView.layoutParams = RecyclerView.LayoutParams(0, 0)
//                Log.i("TodoAdapter",todo.checked.toString())
            }
            binding.memuIcon.setOnClickListener{
                navController= Navigation.findNavController(it)
                val action =
                    MainFragmentDirections.actionMainFragmentToTodoDialogFragment(todo,category)
                navController.navigate(action)
            }
            binding.checkbox.setOnClickListener() {
                todo.checked=it.checkbox.isChecked
                Log.i("TodoAdapter","checked ${todo.id} $it.checkbox.isChecked")
                todoViewModel.updateTodo(todo)
            }
            binding.executePendingBindings()
        }
    }
}