package atanana.com.todoapp.screens.todos

import android.app.Activity
import android.app.Application
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
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class TodosListViewModel(app: Application, private val database: TodosDatabase) :
    TodosViewModel(app) {
    private val todosData = MutableLiveData<List<TodoEntity>>()
    val todos: LiveData<List<TodoEntity>> = todosData

    private val userData = MutableLiveData<UserState>()
    val user: LiveData<UserState> = userData

    init {
        updateUser()
    }

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
            updateUser()
            val response = IdpResponse.fromResultIntent(data)
            if (response != null) {
                showToast(R.string.sign_in_success)
            } else {
                showToast(R.string.error_sign_in)
            }
        }
    }

    private fun updateUser() {
        val firebaseUser = FirebaseAuth.getInstance().currentUser
        if (firebaseUser != null) {
            val name = firebaseUser.displayName ?: app.getString(R.string.unknown_user)
            userData.postValue(UserState.User(name))
        } else {
            userData.postValue(UserState.Anonymous)
        }
    }

    companion object {
        const val REQUEST_CODE_SIGN_IN = 1354
    }
}

sealed class UserState {
    object Anonymous : UserState()
    data class User(val name: String) : UserState()
}