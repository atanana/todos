package atanana.com.todoapp.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "todos")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    var title: String = "",
    var text: String = ""
)