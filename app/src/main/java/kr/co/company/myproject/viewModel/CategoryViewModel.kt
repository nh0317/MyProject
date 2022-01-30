package kr.co.company.myproject.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.toCollection
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kr.co.company.myproject.domain.todo.Todo
import kr.co.company.myproject.domain.TodoListDatabase
import kr.co.company.myproject.domain.category.Category
import kr.co.company.myproject.domain.category.CategoryRepository

class CategoryViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData : LiveData<List<Category>>

    private var _readAllDateCategory = MutableLiveData<List<Category>>()
    val readAllDateCategory : LiveData<List<Category>>
        get() = _readAllDateCategory
//    var todoOfCategory: LiveData<List<Todo>> = MutableLiveData()
    private val repository : CategoryRepository

    init{
        val categoryDao = TodoListDatabase.getDatabase(application)!!.CategoryDao()
        this.repository= CategoryRepository(categoryDao)
        readAllData=repository.readAllData.asLiveData()}
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
    fun readDateCategory(date:Long){
        viewModelScope.launch(Dispatchers.IO){
            var temp = repository.readDateCategory(date)
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