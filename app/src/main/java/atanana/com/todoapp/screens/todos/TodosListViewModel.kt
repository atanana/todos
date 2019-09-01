package atanana.com.todoapp.screens.todos

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import atanana.com.todoapp.R
import atanana.com.todoapp.db.TodoEntity
import atanana.com.todoapp.db.TodosDatabase
import atanana.com.todoapp.screens.TodosViewModel
import atanana.com.todoapp.screens.edittodo.EditTodo
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import kotlinx.coroutines.launch

class TodosListViewModel(private val database: TodosDatabase) : TodosViewModel() {
    private val todosData = MutableLiveData<List<TodoEntity>>()
    val todos: LiveData<List<TodoEntity>> = todosData

    fun loadTodos() {
        viewModelScope.launch {
            todosData.value = database.todosDao().allTodos()
        }
    }

    fun addTodo() {
        goTo(TodosListDirections.actionTodosListToEditTodo(EditTodo.NEW_TODO))
    }

    fun onTodoClick(todoId: Long) {
        goTo(TodosListDirections.actionTodosListToEditTodo(todoId))
    }

    fun onSignInClick() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.GoogleBuilder().build(),
            AuthUI.IdpConfig.EmailBuilder().build()
        )
        val intent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
        startActivity(intent, REQUEST_CODE_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE_SIGN_IN && resultCode == Activity.RESULT_OK) {
            val response = IdpResponse.fromResultIntent(data)
            if (response != null) {
                showToast(R.string.sign_in_success)
            } else {
                showToast(R.string.error_sign_in)
            }
        }
    }

    companion object {
        const val REQUEST_CODE_SIGN_IN = 1354
    }
}