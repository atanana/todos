package atanana.com.todoapp.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert

@Dao
interface TodoDao {
    @Insert
    fun insert(todo: TodoEntity)
}