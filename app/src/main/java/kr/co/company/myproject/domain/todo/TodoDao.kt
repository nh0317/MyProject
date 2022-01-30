package kr.co.company.myproject.domain.todo

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTodo(category : Todo)

    @Update
    suspend fun upadateTodo(category: Todo)

    @Delete
    suspend fun deleteTodo(category: Todo)

    @Query("SELECT * FROM Todo ORDER BY end_date desc")
    fun readAllData() : Flow<List<Todo>>

    @Query("SELECT * FROM Todo WHERE category_id=:categoryId and not checked order by end_date desc")
    fun readAllCategoryTodo(categoryId:Long) : List<Todo>
}