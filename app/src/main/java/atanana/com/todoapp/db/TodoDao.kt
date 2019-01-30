package atanana.com.todoapp.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(todo: TodoEntity): Long

    @Query("select * from todos")
    fun allTodos(): List<TodoEntity>

    @Query("select * from todos where id = :id")
    fun byId(id: Long): TodoEntity?
}