package atanana.com.todoapp.screens.todos


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import atanana.com.todoapp.R
import atanana.com.todoapp.screens.TodosFragment
import kotlinx.android.synthetic.main.fragment_todos_list.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.support.closestKodein
import org.kodein.di.generic.instance

class TodosList : TodosFragment(), KodeinAware {
    override val kodein by closestKodein()

    private val presenter: TodosListPresenter by instance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todos_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        add_todo.setOnClickListener {
            presenter.addTodo()
        }

        todos_list.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(
            requireContext(),
            androidx.recyclerview.widget.LinearLayoutManager.VERTICAL,
            false
        )
        todos_list.adapter = presenter.adapter
    }

    override fun onResume() {
        super.onResume()

        uiScope.launch {
            presenter.loadTodos()
        }
    }

    companion object {
        fun newInstance(): TodosList = TodosList()
    }
}
