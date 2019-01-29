package atanana.com.todoapp.screens.edittodo


import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import atanana.com.todoapp.R
import atanana.com.todoapp.db.TodoEntity
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

    private val presenter: EditTodoPresenter by instance(arg = this)

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

        uiScope.launch { presenter.onViewCreated() }
    }

    fun setTodo(todoEntity: TodoEntity) {
        todo_title.text = SpannableStringBuilder(todoEntity.title)
        todo_text.text = SpannableStringBuilder(todoEntity.text)
    }

    override fun onBackPressed() {
        GlobalScope.launch {
            presenter.onBackPress()
        }
    }

    companion object {
        const val KEY_TODO = "todo"

        fun newInstance(): EditTodo = EditTodo()

        fun newInstance(todoId: Long) = EditTodo().apply {
            arguments = Bundle().apply {
                putLong(KEY_TODO, todoId)
            }
        }
    }
}
