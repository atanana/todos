package atanana.com.todoapp.screens.todos

import androidx.fragment.app.FragmentManager
import atanana.com.todoapp.R
import atanana.com.todoapp.db.TodosDatabase
import atanana.com.todoapp.screens.edittodo.EditTodo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TodosListPresenter(
    private val fragmentManager: FragmentManager,
    private val database: TodosDatabase
) {
    val adapter = TodosListAdapter { todoId ->
        openFragment(EditTodo.newInstance(todoId))
    }

    fun addTodo() {
        openFragment(EditTodo.newInstance())
    }

    private fun openFragment(fragment: EditTodo) {
        fragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
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