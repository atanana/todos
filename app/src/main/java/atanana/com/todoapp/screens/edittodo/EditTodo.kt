package atanana.com.todoapp.screens.edittodo


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import atanana.com.todoapp.R
import atanana.com.todoapp.screens.TodosFragment
import org.kodein.di.KodeinAware
import org.kodein.di.android.support.closestKodein
import org.kodein.di.generic.instance

class EditTodo : TodosFragment(), KodeinAware {
    override val kodein by closestKodein()

    private val presenter: EditTodoPresenter by instance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_todo, container, false)
    }

    override fun onBackPressed() {
        presenter.onBackPress()
    }

    companion object {
        fun newInstance(): EditTodo = EditTodo()
    }
}
