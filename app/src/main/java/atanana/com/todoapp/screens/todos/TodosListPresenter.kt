package atanana.com.todoapp.screens.todos

import android.content.Context
import android.widget.Toast

class TodosListPresenter(private val context: Context) {
    fun addTodo() {
        Toast.makeText(context, "add todo", Toast.LENGTH_SHORT).show()
    }
}