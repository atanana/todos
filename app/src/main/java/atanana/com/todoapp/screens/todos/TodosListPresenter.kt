package atanana.com.todoapp.screens.todos

import android.support.v4.app.FragmentManager
import atanana.com.todoapp.R
import atanana.com.todoapp.db.TodosDatabase
import atanana.com.todoapp.screens.edittodo.EditTodo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TodosListPresenter(
    private val fragmentManager: FragmentManager,
    private val database: TodosDatabase
) {
    val adapter = TodosListAdapter()

    fun addTodo() {
        fragmentManager.beginTransaction()
            .replace(R.id.container, EditTodo.newInstance())
            .addToBackStack(EditTodo::class.java.name)
            .commit()
    }

    suspend fun loadTodos() {
        val todos = withContext(Dispatchers.IO) {
            val todosDao = database.todosDao()
            todosDao.allTodos()
        }
        adapter.submitList(todos)
    }
}