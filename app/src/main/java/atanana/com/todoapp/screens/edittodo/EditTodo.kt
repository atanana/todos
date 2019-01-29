package atanana.com.todoapp.screens.edittodo


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import atanana.com.todoapp.R
import atanana.com.todoapp.screens.TodosFragment
import atanana.com.todoapp.ui.TextWatcherAdapter
import kotlinx.android.synthetic.main.fragment_edit_todo.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        todo_title.addTextChangedListener(TextWatcherAdapter(presenter::updateTitle))
        todo_text.addTextChangedListener(TextWatcherAdapter(presenter::updateText))
    }

    override fun onBackPressed() {
        GlobalScope.launch {
            presenter.onBackPress()
        }
    }

    companion object {
        fun newInstance(): EditTodo = EditTodo()
    }
}
