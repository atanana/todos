package atanana.com.todoapp.screens.edittodo

import atanana.com.todoapp.db.TodoEntity
import atanana.com.todoapp.db.TodosDatabase
import atanana.com.todoapp.screens.edittodo.EditTodo.Companion.KEY_TODO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EditTodoPresenter(private val database: TodosDatabase, private val view: EditTodo) {
    private var todo = TodoEntity()

    fun updateTitle(title: String) {
        todo.title = title
    }

    fun updateText(text: String) {
        todo.text = text
    }

    suspend fun onBackPress() = withContext(Dispatchers.IO) {
        if (todo.title.isNotEmpty() || todo.text.isNotEmpty()) {
            val dao = database.todosDao()
            dao.insert(todo)
        }
    }

    suspend fun onViewCreated() = withContext(Dispatchers.IO) {
        val todoId = view.arguments?.getLong(KEY_TODO, -1) ?: -1
        val newTodo = database.todosDao().byId(todoId)
        if (newTodo != null) {
            todo = newTodo
            withContext(Dispatchers.Main) {
                view.setTodo(newTodo)
            }
        }
    }
}