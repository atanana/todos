package atanana.com.todoapp.screens.todos

import android.support.v4.app.FragmentManager
import atanana.com.todoapp.R
import atanana.com.todoapp.screens.edittodo.EditTodo

class TodosListPresenter(private val fragmentManager: FragmentManager) {
    fun addTodo() {
        fragmentManager.beginTransaction()
            .replace(R.id.container, EditTodo.newInstance())
            .addToBackStack(EditTodo::class.java.name)
            .commit()
    }
}