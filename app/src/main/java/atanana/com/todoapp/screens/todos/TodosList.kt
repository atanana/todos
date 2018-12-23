package atanana.com.todoapp.screens.todos


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import atanana.com.todoapp.R
import kotlinx.android.synthetic.main.fragment_todos_list.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.support.closestKodein
import org.kodein.di.generic.instance

class TodosList : Fragment(), KodeinAware {
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
    }

    companion object {
        fun newInstance(): TodosList = TodosList()
    }
}
