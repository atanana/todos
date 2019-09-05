package atanana.com.todoapp.screens.todos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import atanana.com.todoapp.R
import atanana.com.todoapp.data.Todo
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_todo.*

class TodosListAdapter(private val onTodoClick: (todoId: String) -> Unit) :
    ListAdapter<Todo, TodoViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): TodoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_todo, parent, false) as ViewGroup
        return TodoViewHolder(view, onTodoClick)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Todo>() {
            override fun areItemsTheSame(p0: Todo, p1: Todo): Boolean = p0.id == p1.id

            override fun areContentsTheSame(p0: Todo, p1: Todo): Boolean = p0 == p1
        }
    }
}

class TodoViewHolder(override val containerView: View, private val onTodoClick: (todoId: String) -> Unit) :
    androidx.recyclerview.widget.RecyclerView.ViewHolder(containerView), LayoutContainer {
    fun bind(todo: Todo) {
        todo_title.text = todo.title
        containerView.setOnClickListener { onTodoClick(todo.id) }
    }
}