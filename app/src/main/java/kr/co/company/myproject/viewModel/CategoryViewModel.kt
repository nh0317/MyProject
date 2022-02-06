package kr.co.company.myproject.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kr.co.company.myproject.domain.todo.Todo
import kr.co.company.myproject.domain.TodoListDatabase
import kr.co.company.myproject.domain.category.Category
import kr.co.company.myproject.domain.category.CategoryRepository
import kr.co.company.myproject.domain.relation.CategoryWithTodo
import java.time.LocalDate
import java.time.LocalDateTime

class CategoryViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData : LiveData<List<CategoryWithTodo>>

    private var _readAllDateCategory = MutableLiveData<List<CategoryWithTodo>>()
    val readAllDateCategory : LiveData<List<CategoryWithTodo>>
        get() = _readAllDateCategory

    private var _readOneCategory = MutableLiveData<CategoryWithTodo>()
    val readOneCategory : LiveData<CategoryWithTodo>
        get() = _readOneCategory

    private val repository : CategoryRepository
//    var isTodoExpanded = false
//    var isCategoryExpanded = false

    init{
        val categoryDao = TodoListDatabase.getDatabase(application)!!.CategoryDao()
        this.repository= CategoryRepository(categoryDao)
        readAllData=repository.readAllData
//            .map { it.map {
//                val todos = it.todos.filter { it.checked }
//                CategoryWithTodo(it.category,todos)
//            }}
            .asLiveData()
    }
    fun addCategory(category: Category){
        viewModelScope.launch(Dispatchers.IO){
            repository.addCategory(category)
        }
    }
    fun updateCategory(category: Category){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateCategory(category)
        }
    }
    fun deleteCategory(category: Category){
        viewModelScope.launch(Dispatchers.IO) { repository.deleteCategory(category) }
    }
    fun readAllDateCategory(date: LocalDate){
        viewModelScope.launch(Dispatchers.IO){
            val temp = repository.readDateCategory(date)
            _readAllDateCategory.postValue(temp)
        }
    }
//    fun getTodosByCategory(id:Long) : List<Todo>?{
//        var todoList : Flow<List<Todo>> = emptyFlow()
//        viewModelScope.launch ( Dispatchers.IO ){
//            todoList = repository.findAllTodoByCategoryId(id)
//            todoOfCategory=todoList.asLiveData()
//        }
//        return todoList.asLiveData().value
//    }
}