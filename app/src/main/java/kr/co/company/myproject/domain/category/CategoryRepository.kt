package kr.co.company.myproject.domain.category

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.*
import kr.co.company.myproject.domain.relation.CategoryWithTodo
import kr.co.company.myproject.domain.todo.Todo
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.streams.toList


class CategoryRepository(private val categoryDao : CategoryDao) {
    val readAllData : Flow<List<CategoryWithTodo>> = categoryDao.readAllData()

    suspend fun addCategory(category : Category){
        categoryDao.addCategory(category)
    }

    suspend fun updateCategory(category: Category){
        categoryDao.upadateCategory(category)
    }

    suspend fun deleteCategory(category: Category){
        categoryDao.deleteCategory(category)
    }

    fun readDateCategory(date: LocalDate):List<CategoryWithTodo>{
        return categoryDao.readAllDateCategory(date)
    }

    fun readOneCategory(categoryId:Long):CategoryWithTodo{
        return categoryDao.readOneCategory(categoryId)
    }

}