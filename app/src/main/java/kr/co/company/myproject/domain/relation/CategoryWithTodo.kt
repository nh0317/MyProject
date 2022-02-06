package kr.co.company.myproject.domain.relation

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import kotlinx.android.parcel.Parcelize
import kr.co.company.myproject.domain.todo.Todo
import kr.co.company.myproject.domain.category.Category

@Parcelize
data class CategoryWithTodo(@Embedded var category: Category,
                            @Relation(
                                parentColumn = "category_id",
                                entityColumn = "category_id"
                            )
                            var todos: List<Todo>) :Parcelable{
}