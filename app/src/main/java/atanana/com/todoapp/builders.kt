package atanana.com.todoapp

import android.content.Context
import androidx.room.Room
import atanana.com.todoapp.db.TodosDatabase

fun buildDatabase(context: Context): TodosDatabase =
    Room.databaseBuilder(context, TodosDatabase::class.java, "todos.db")
        .build()