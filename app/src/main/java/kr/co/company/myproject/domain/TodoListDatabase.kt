package kr.co.company.myproject.domain

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import kr.co.company.myproject.domain.todo.Todo
import kr.co.company.myproject.domain.todo.TodoDao
import kr.co.company.myproject.domain.category.Category
import kr.co.company.myproject.domain.category.CategoryDao

@TypeConverters(LocalDateConverter::class)
@Database(entities = [Category::class, Todo::class], version = 14, exportSchema = false)
abstract class TodoListDatabase : RoomDatabase() {
    abstract fun CategoryDao() : CategoryDao
    abstract fun TodoDao() : TodoDao

    companion object{
        @Volatile
        private var instance : TodoListDatabase?=null

        fun getDatabase(context:Context):TodoListDatabase?{
            if (instance==null){
                synchronized(TodoListDatabase::class){
                    instance= Room.databaseBuilder(
                        context.applicationContext,
                        TodoListDatabase::class.java,
                        "todo_database"
                    ).fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return instance
        }
    }
}