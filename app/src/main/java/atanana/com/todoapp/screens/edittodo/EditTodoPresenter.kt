package atanana.com.todoapp.screens.edittodo

import atanana.com.todoapp.db.TodoEntity
import atanana.com.todoapp.db.TodosDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EditTodoPresenter(private val database: TodosDatabase) {
    private var todo = TodoEntity()

    suspend fun onBackPress() = withContext(Dispatchers.IO) {
        val dao = database.todosDao()
        dao.insert(todo)
    }
}