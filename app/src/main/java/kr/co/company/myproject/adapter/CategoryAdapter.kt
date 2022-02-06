package kr.co.company.myproject.adapter

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.company.myproject.databinding.CategoryItemLayoutBinding
import kr.co.company.myproject.domain.todo.Todo
import kr.co.company.myproject.domain.relation.CategoryWithTodo
import kr.co.company.myproject.fragment.MainFragmentDirections
import kr.co.company.myproject.viewModel.CategoryViewModel
import kr.co.company.myproject.viewModel.TodoViewModel
import java.util.Collections.emptyList

class CategoryAdapter(private val context: Context,
                      private val tabPosition: Int,
                      private val lifecycleOwner: LifecycleOwner,
                      private val categoryViewModel: CategoryViewModel,
                      private val todoViewModel: TodoViewModel)
    : RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {
    private var categoryList = emptyList<CategoryWithTodo>()
//    private var isExpanded = false
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = CategoryItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    return MyViewHolder(binding,lifecycleOwner)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(categoryList[position],categoryViewModel)
    }

    fun setData(it: List<CategoryWithTodo>) {
        this.categoryList=it
        notifyDataSetChanged()
    }
//    fun setData(it: List<CategoryWithTodo>) {
//        this.categoryWithTodo=it
//        notifyDataSetChanged()
//    }

    fun getCategoryId(position: Int): Long {
        return position.toLong()
    }
    fun getCategoryId():Int{
        return getCategoryId().toInt()
    }


    inner class MyViewHolder(private val binding: CategoryItemLayoutBinding,
                             private val lifecycleOwner: LifecycleOwner)
        : RecyclerView.ViewHolder(binding.root) {
        lateinit var navController: NavController
        @RequiresApi(Build.VERSION_CODES.N)
        fun bind(categoryWithTodo : CategoryWithTodo, categoryViewModel: CategoryViewModel){
            val pref = context.getSharedPreferences("viewInfo",Context.MODE_PRIVATE)
            val visibility= pref.getInt("tba${tabPosition}id${categoryWithTodo.category.id}",View.VISIBLE)

            binding.category=categoryWithTodo.category
//            Log.i("categoryAdapter",categoryWithTodo.category.id.toString())
//            categoryWithTodo.todos.forEach{todo->Log.i("categoryAdapter",todo.categoryId.toString())}
            binding.memuIcon.setOnClickListener{
                navController=Navigation.findNavController(it)
                val action =
                    MainFragmentDirections.actionMainFragmentToCategoryDialogFragment2(categoryWithTodo.category)
                navController.navigate(action)
            }

            binding.plusIcon.setOnClickListener{
                navController=Navigation.findNavController(it)
                val action =
                    MainFragmentDirections.actionMainFragmentToTodoDialogFragment(
                        Todo(categoryWithTodo.category.id, categoryWithTodo.category.startDate,categoryWithTodo.category.endDate),
                        categoryWithTodo.category)
                navController.navigate(action)
            }

            val todoAdapter  = TodoAdapter(context,todoViewModel, categoryViewModel,categoryWithTodo.category)
            categoryViewModel.readAllData.observe(lifecycleOwner, Observer {
                todoAdapter.setData(categoryWithTodo.todos)
            })
            binding.todoRecycle.apply {
                this.layoutManager=LinearLayoutManager(context)
                this.adapter = todoAdapter
            }
            Log.i("CategoryAdapter","tab ${tabPosition} ${categoryWithTodo.category.id} $visibility")
            binding.todoRecycle.visibility=visibility
            binding.categoryContainer.setOnClickListener(null)
            binding.categoryContainer.setOnClickListener{
                if (binding.todoRecycle.visibility==View.VISIBLE){
                    binding.todoRecycle.visibility= View.GONE
                    pref.edit().putInt("tba${tabPosition}id${categoryWithTodo.category.id}",View.GONE).apply()
                }
                else if(binding.todoRecycle.visibility==View.GONE) {
//                    binding.todoRecycle.measure(ViewGroup.LayoutParams.MATCH_PARENT,
//                        ViewGroup.LayoutParams.WRAP_CONTENT)
                    binding.todoRecycle.visibility=View.VISIBLE
                    pref.edit().putInt("tba${tabPosition}id${categoryWithTodo.category.id}",View.VISIBLE).apply()
                }
            }
            binding.executePendingBindings()
        }

    }
}