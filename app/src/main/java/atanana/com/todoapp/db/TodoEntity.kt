package atanana.com.todoapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import atanana.com.todoapp.data.Todo

@Entity(tableName = "todos")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    var title: String = "",
    var text: String = ""
) {
    fun toTodo() = Todo(id.toString(), title, text)
}

fun Todo.toEntity() = TodoEntity(id.toLong(), title, text)