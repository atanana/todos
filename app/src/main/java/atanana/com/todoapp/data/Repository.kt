package atanana.com.todoapp.data

import atanana.com.todoapp.db.TodoEntity
import atanana.com.todoapp.db.TodosDatabase
import atanana.com.todoapp.db.toEntity

class Repository(database: TodosDatabase) {
    private val todoDao = database.todosDao()

    suspend fun allTodos(): List<Todo> = todoDao.allTodos()
        .map(TodoEntity::toTodo)

    suspend fun add(todo: Todo) {
        todoDao.insert(todo.toEntity())
    }

    suspend fun byId(id: Long): Todo? = todoDao.byId(id)?.toTodo()
}