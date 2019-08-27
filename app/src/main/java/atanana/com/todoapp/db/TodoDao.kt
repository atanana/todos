package atanana.com.todoapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(todo: TodoEntity): Long

    @Query("select * from todos")
    suspend fun allTodos(): List<TodoEntity>

    @Query("select * from todos where id = :id")
    fun byId(id: Long): TodoEntity?
}