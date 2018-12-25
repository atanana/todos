package atanana.com.todoapp.screens.edittodo


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import atanana.com.todoapp.R
import atanana.com.todoapp.screens.TodosFragment

class EditTodo : TodosFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_todo, container, false)
    }

    companion object {
        fun newInstance(): EditTodo = EditTodo()
    }
}
