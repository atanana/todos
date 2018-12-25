package atanana.com.todoapp.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface TodoDao {
    @Insert
    fun insert(todo: TodoEntity)

    @Query("select * from todos")
    fun allTodos(): List<TodoEntity>
}