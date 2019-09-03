package atanana.com.todoapp.screens.edittodo

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import atanana.com.todoapp.data.Repository
import atanana.com.todoapp.data.Todo
import atanana.com.todoapp.screens.TodosViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditTodoViewModel(app: Application, private val repository: Repository) :
    TodosViewModel(app) {
    private val todoData = MutableLiveData<Todo>()
    val todo: LiveData<Todo> = todoData

    fun init(args: EditTodoArgs) {
        viewModelScope.launch(Dispatchers.IO) {
            val todo = repository.byId(args.todoId)
            todoData.postValue(todo ?: Todo())
        }
    }

    fun updateTodo(title: String, text: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val todo = todo.value!!
            todo.title = title
            todo.text = text
            repository.add(todo)
            goBack()
        }
    }
}