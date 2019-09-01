package atanana.com.todoapp.screens.todos


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import atanana.com.todoapp.R
import atanana.com.todoapp.screens.TodosFragment
import kotlinx.android.synthetic.main.fragment_todos_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class TodosList : TodosFragment<TodosListViewModel>() {
    override val model: TodosListViewModel by viewModel()

    private val adapter = TodosListAdapter { todoId ->
        model.onTodoClick(todoId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_todos_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        add_todo.setOnClickListener {
            model.addTodo()
        }
        sign_in.setOnClickListener { model.onSignInClick() }

        todos_list.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        todos_list.adapter = adapter

        model.todos.observe(viewLifecycleOwner, Observer { todos ->
            adapter.submitList(todos)
        })
    }

    override fun onResume() {
        super.onResume()
        model.loadTodos()
    }
}
