package atanana.com.todoapp

import android.arch.persistence.room.Room
import android.content.Context
import atanana.com.todoapp.db.TodosDatabase

fun buildDatabase(context: Context): TodosDatabase =
    Room.databaseBuilder(context, TodosDatabase::class.java, "todos.db")
        .build()