package atanana.com.todoapp.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class TodoEntity(
    @PrimaryKey
    val id: Long? = null,
    val title: String = "",
    val text: String = ""
)