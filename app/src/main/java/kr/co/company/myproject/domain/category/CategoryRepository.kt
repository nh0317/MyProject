package kr.co.company.myproject.domain.category

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import kr.co.company.myproject.domain.todo.Todo


class CategoryRepository(private val categoryDao : CategoryDao) {
    val readAllData : Flow<List<Category>> = categoryDao.readAllData()

    suspend fun addCategory(category : Category){
        categoryDao.addCategory(category)
    }

    suspend fun updateCategory(category: Category){
        categoryDao.upadateCategory(category)
    }

    suspend fun deleteCategory(category: Category){
        categoryDao.deleteCategory(category)
    }

    fun readDateCategory(date:Long):List<Category>{
        return categoryDao.readAllDateCategory(date)
    }

}