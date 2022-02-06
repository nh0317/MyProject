package kr.co.company.myproject.domain.category

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import kr.co.company.myproject.domain.relation.CategoryWithTodo
import kr.co.company.myproject.domain.todo.Todo
import java.time.LocalDate

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCategory(category : Category)

    @Update
    suspend fun upadateCategory(category: Category)

    @Delete
    suspend fun deleteCategory(category: Category)

    @Query("SELECT * FROM Category ORDER BY category_end_date desc")
    fun readAllData() : Flow<List<CategoryWithTodo>>

    @Query("SELECT * FROM Category Where  category_start_date <= :date and :date<= category_end_date order by category_end_date desc")
    fun readAllDateCategory(date:LocalDate):List<CategoryWithTodo>

    @Query("SELECT * FROM Category Where category_id = :categoryId order by category_end_date desc")
    fun readOneCategory(categoryId:Long) : CategoryWithTodo

}