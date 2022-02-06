package kr.co.company.myproject.domain.todo

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTodo(category : Todo)

    @Update
    suspend fun updatedTodo(category: Todo)

    @Delete
    suspend fun deleteTodo(category: Todo)

    @Query("SELECT * FROM Todo ORDER BY todo_end_date desc")
    fun readAllData() : Flow<List<Todo>>

    @Query("SELECT * FROM Todo WHERE category_id=:categoryId and not todo_checked order by todo_end_date desc")
    fun readAllCategoryTodo(categoryId:Long) : List<Todo>
}