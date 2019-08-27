package atanana.com.todoapp.screens.todos

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import atanana.com.todoapp.db.TodoEntity
import atanana.com.todoapp.db.TodosDatabase
import atanana.com.todoapp.screens.TodosViewModel
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance

class TodosListViewModel(app: Application) : TodosViewModel(app) {
    private val database: TodosDatabase by instance()

    private val todos = MutableLiveData<List<TodoEntity>>()
    val todosData: LiveData<List<TodoEntity>> = todos

    init {
        viewModelScope.launch {
            todos.value = database.todosDao().allTodos()
        }
    }
}