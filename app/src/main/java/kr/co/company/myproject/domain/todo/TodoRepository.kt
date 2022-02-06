package kr.co.company.myproject.domain.todo

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow


class TodoRepository(private val todoDao : TodoDao) {
//    var categoryId: Long?=null
    val readAllData : Flow<List<Todo>> = emptyFlow()

    suspend fun addTodo(todo : Todo){
        todoDao.addTodo(todo)
    }

    suspend fun updateTodo(todo: Todo){
        todoDao.updatedTodo(todo)
    }

    suspend fun deleteTodo(todo: Todo){
        todoDao.deleteTodo(todo)
    }
//    suspend fun findAllTodoByCategoryId(categoryId:Long):Flow<List<Todo>>
//            = todoDao.readAllCategoryTodo(categoryId)

    fun readAllData(categoryId: Long): List<Todo> {
//        Log.i("TodoRepository",todoDao.readAllCategoryTodo(categoryId).size.toString())
        return todoDao.readAllCategoryTodo(categoryId)
    }
}