package atanana.com.todoapp.screens.edittodo


import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import atanana.com.todoapp.R
import atanana.com.todoapp.data.Todo
import atanana.com.todoapp.screens.TodosFragment
import kotlinx.android.synthetic.main.fragment_edit_todo.*
import org.koin.android.viewmodel.ext.android.viewModel

class EditTodo : TodosFragment<EditTodoViewModel>() {
    private val args: EditTodoArgs by navArgs()

    override val model: EditTodoViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_edit_todo, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        model.init(args)
        model.todo.observe(viewLifecycleOwner, Observer { todo ->
            setTodo(todo)
        })

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            isEnabled = false
            val title = todo_title.text.toString()
            val text = todo_text.text.toString()
            model.updateTodo(title, text)
        }
    }

    fun setTodo(todo: Todo) {
        todo_title.text = SpannableStringBuilder(todo.title)
        todo_text.text = SpannableStringBuilder(todo.text)
    }
}
