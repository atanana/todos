package atanana.com.todoapp.screens.edittodo

import atanana.com.todoapp.db.TodoEntity
import atanana.com.todoapp.db.TodosDatabase

class EditTodoPresenter(private val database: TodosDatabase) {
    private var todo = TodoEntity()

    fun onBackPress() {
        val dao = database.todosDao()
        dao.insert(todo)
    }
}