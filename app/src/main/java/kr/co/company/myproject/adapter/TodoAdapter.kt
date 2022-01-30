package kr.co.company.myproject.adapter

import android.opengl.Visibility
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import kr.co.company.myproject.databinding.TodoItemLayoutBinding
import kr.co.company.myproject.domain.category.Category
import kr.co.company.myproject.domain.todo.Todo
import kr.co.company.myproject.fragment.MainFragmentDirections
import kr.co.company.myproject.viewModel.TodoViewModel
import java.util.Collections.emptyList

class TodoAdapter(private val todoViewModel: TodoViewModel, categoryId:Long)
    : RecyclerView.Adapter<TodoAdapter.MyViewHolder>() {
      private var todoList = emptyList<Todo>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = TodoItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(todoList[position],todoViewModel)
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

    class MyViewHolder(private val binding: TodoItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        lateinit var navController: NavController
        fun bind(todo : Todo, todoViewModel: TodoViewModel){
            binding.todo=todo
            binding.memuIcon.setOnClickListener{
                navController= Navigation.findNavController(it)
                val action =
                    MainFragmentDirections.actionMainFragmentToTodoDialogFragment(todo)
                navController.navigate(action)
            }
            binding.checkbox.setOnCheckedChangeListener(null)
            binding.checkbox.setOnCheckedChangeListener { _, check ->
                Log.i("TodoAdapter","checked")
                todo.checked=!check
                todoViewModel.updateTodo(todo)
            }
//            val newTodo = Todo(todo.startYear,todo.startMonth,todo.startDay,
//            todo.endYear,todo.endMonth,todo.endDay,todo.name,todo.memo)
//            todoViewModel.updateTodo(newTodo)
            binding.executePendingBindings()
        }
    }
}