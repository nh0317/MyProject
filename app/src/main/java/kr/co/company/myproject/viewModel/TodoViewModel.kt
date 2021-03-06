package kr.co.company.myproject.viewModel

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.company.myproject.domain.todo.Todo
import kr.co.company.myproject.domain.todo.TodoRepository
import kr.co.company.myproject.domain.TodoListDatabase

class TodoViewModel(application: Application) : AndroidViewModel(application) {
//    val readAllData : LiveData<List<Todo>>
    private var _todoOfCategory =MutableLiveData<List<Todo>>()
    val todoOfCategory : LiveData<List<Todo>>
        get()=_todoOfCategory

    private val _repository : TodoRepository

    init{
        val todoDao = TodoListDatabase.getDatabase(application)!!.TodoDao()
        this._repository= TodoRepository(todoDao)}
//        readAllData=repository.readAllData.asLiveData()}

//    constructor(application: Application,categoryId: Long):this(application){
//        todoOfCategory=repository.readAllData(categoryId).asLiveData()
//    }
    fun addTodo(todo: Todo){
        viewModelScope.launch(Dispatchers.IO){
            _repository.addTodo(todo)
        }
    }
    fun updateTodo(todo: Todo){
        viewModelScope.launch(Dispatchers.IO){
            _repository.updateTodo(todo)
        }
    }
    fun deleteTodo(todo: Todo){
        viewModelScope.launch(Dispatchers.IO) { _repository.deleteTodo(todo) }
    }

//    fun readTodo(categoryId: Long){
//        viewModelScope.launch (Dispatchers.IO){
//            val tmp = _repository.readAllData(categoryId)
//            _todoOfCategory.postValue(tmp)
//        }
//    }
}