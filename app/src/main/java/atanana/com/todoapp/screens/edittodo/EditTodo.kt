package atanana.com.todoapp.screens.edittodo


import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import atanana.com.todoapp.R
import atanana.com.todoapp.db.TodoEntity
import atanana.com.todoapp.screens.TodosFragment
import kotlinx.android.synthetic.main.fragment_edit_todo.*
import org.koin.android.viewmodel.ext.android.viewModel

class EditTodo : TodosFragment<EditTodoViewModel>() {
    private val args: EditTodoArgs by navArgs()

    override val model: EditTodoViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        model.init(args)
        model.todo.observe(viewLifecycleOwner, Observer { todo ->
            setTodo(todo)
        })
//        todo_title.addTextChangedListener(TextWatcherAdapter(presenter::updateTitle))
//        todo_text.addTextChangedListener(TextWatcherAdapter(presenter::updateText))
//
//        uiScope.launch { presenter.onViewCreated() }
    }

    fun setTodo(todoEntity: TodoEntity) {
        todo_title.text = SpannableStringBuilder(todoEntity.title)
        todo_text.text = SpannableStringBuilder(todoEntity.text)
    }

    companion object {
        const val NEW_TODO = -1L
    }
}
