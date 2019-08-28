package atanana.com.todoapp.screens.todos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import atanana.com.todoapp.db.TodoEntity
import atanana.com.todoapp.db.TodosDatabase
import atanana.com.todoapp.screens.TodosViewModel
import kotlinx.coroutines.launch

class TodosListViewModel(private val database: TodosDatabase) : TodosViewModel() {
    private val todosData = MutableLiveData<List<TodoEntity>>()
    val todos: LiveData<List<TodoEntity>> = todosData

    fun loadTodos() {
        viewModelScope.launch {
            todosData.value = database.todosDao().allTodos()
        }
    }
}