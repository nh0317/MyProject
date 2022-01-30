package kr.co.company.myproject.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.company.myproject.databinding.CategoryItemLayoutBinding
import kr.co.company.myproject.domain.todo.Todo
import kr.co.company.myproject.domain.category.Category
import kr.co.company.myproject.domain.relation.CategoryWithTodo
import kr.co.company.myproject.fragment.MainFragmentDirections
import kr.co.company.myproject.viewModel.CategoryViewModel
import kr.co.company.myproject.viewModel.TodoViewModel
import java.util.*
import java.util.Collections.emptyList

class CategoryAdapter(private val context: Context,
                      private val lifecycleOwner: LifecycleOwner,
                      private val categoryViewModel: CategoryViewModel,
                      private val todoViewModel: TodoViewModel)
    : RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {
    private var categoryList = emptyList<Category>()
    private var categoryWithTodo = emptyList<CategoryWithTodo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = CategoryItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding,lifecycleOwner,context)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(categoryList[position],categoryViewModel)
    }

    fun setData(it: List<Category>) {
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
                             private val lifecycleOwner: LifecycleOwner,
                             private var context: Context)
        : RecyclerView.ViewHolder(binding.root) {
        lateinit var navController: NavController
        fun bind(category : Category,categoryViewModel: CategoryViewModel){
            binding.category=category
            binding.memuIcon.setOnClickListener{
                navController=Navigation.findNavController(it)
                val action =
                    MainFragmentDirections.actionMainFragmentToCategoryDialogFragment2(category)
                navController.navigate(action)
            }

            binding.plusIcon.setOnClickListener{
                navController=Navigation.findNavController(it)
                val action =
                    MainFragmentDirections.actionMainFragmentToTodoDialogFragment(Todo(category.id))
                navController.navigate(action)
            }

//            todoViewModel.todoOfCategory.observe(binding.lifecycleOwner!!, Observer {

            val todoAdapter : TodoAdapter = TodoAdapter(todoViewModel,category.id)

            Log.i("CategoryAdapter",category.id.toString())
            todoViewModel.todoOfCategory.observe(lifecycleOwner,Observer {
                todoViewModel.readTodo(category.id)
//                todoViewModel.readTodo(category.id)
                Log.i("CategoryAdapter","todo list size"+it.size.toString())
                todoAdapter.setData(it)
            })
//            todoViewModel.todoOfCategory.observe(lifecycleOwner,Observer {
//                todoViewModel.readTodo(category.id)
//            })
            binding.todoRecycle.apply {
                this.layoutManager=LinearLayoutManager(context)
                this.adapter = todoAdapter
            }
//            binding.todoRecycle.setHasFixedSize(true)
//            binding.todoRecycle.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
//            binding.todoRecycle.adapter = todoAdapter
            binding.executePendingBindings()
//            })
//            todoAdapter.setData(todoViewModel.todoOfCategory.value!!)
//            val newCategory = Category(category.startYear,category.startMonth,category.startDay,
//            category.endYear,category.endMonth,category.endDay,category.name,category.memo)
//            categoryViewModel.updateCategory(newCategory)
        }

    }
}