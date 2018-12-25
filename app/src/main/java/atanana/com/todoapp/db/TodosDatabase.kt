package atanana.com.todoapp.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [TodoEntity::class], version = 1)
abstract class TodosDatabase : RoomDatabase() {
    abstract fun todosDao(): TodoDao
}