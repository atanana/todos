package atanana.com.todoapp.db

import android.arch.persistence.room.Entity

@Entity
data class TodoEntity(
    val id: Long?,
    val title: String,
    val text: String
)