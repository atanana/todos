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

class TodosList : TodosFragment() {
    private val listViewModel: TodosListViewModel by viewModel()

    private val adapter = TodosListAdapter { todoId ->
        //        openFragment(EditTodo.newInstance(todoId))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todos_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        add_todo.setOnClickListener {
            //            presenter.addTodo()
        }

        todos_list.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        todos_list.adapter = adapter

        listViewModel.todosData.observe(viewLifecycleOwner, Observer { todos ->
            adapter.submitList(todos)
        })
    }

    override fun onResume() {
        super.onResume()
        listViewModel.loadTodos()
    }

    companion object {
        fun newInstance(): TodosList = TodosList()
    }
}
