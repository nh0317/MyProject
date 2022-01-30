package kr.co.company.myproject.domain.category

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import kr.co.company.myproject.domain.todo.Todo

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCategory(category : Category)

    @Update
    suspend fun upadateCategory(category: Category)

    @Delete
    suspend fun deleteCategory(category: Category)

    @Query("SELECT * FROM Category ORDER BY end_date desc")
    fun readAllData() : Flow<List<Category>>

    @Query("SELECT * FROM Category Where  start_date < :date and :date<end_date")
    fun readAllDateCategory(date:Long):List<Category>


}