package atanana.com.todoapp.screens.edittodo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import atanana.com.todoapp.db.TodoEntity
import atanana.com.todoapp.db.TodosDatabase
import atanana.com.todoapp.screens.TodosViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditTodoViewModel(private val database: TodosDatabase) : TodosViewModel() {
    private val todoData = MutableLiveData<TodoEntity>()
    val todo: LiveData<TodoEntity> = todoData

    fun init(args: EditTodoArgs) {
        viewModelScope.launch(Dispatchers.IO) {
            val todo = database.todosDao().byId(args.todoId)
            todoData.postValue(todo ?: TodoEntity())
        }
    }

    fun updateTodo(title: String, text: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val todo = todo.value
            if (todo != null) {
                todo.title = title
                todo.text = text
                database.todosDao().insert(todo)
            }
        }
    }
}