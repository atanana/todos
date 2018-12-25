package atanana.com.todoapp.screens.todos

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import atanana.com.todoapp.R
import atanana.com.todoapp.db.TodoEntity

class TodosListAdapter : ListAdapter<TodoEntity, TodoViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): TodoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_todo, parent, false) as ViewGroup
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TodoEntity>() {
            override fun areItemsTheSame(p0: TodoEntity, p1: TodoEntity): Boolean = p0.id == p1.id

            override fun areContentsTheSame(p0: TodoEntity, p1: TodoEntity): Boolean = p0 == p1
        }
    }
}

class TodoViewHolder(view: ViewGroup) : RecyclerView.ViewHolder(view) {
    fun bind(todo: TodoEntity) {

    }
}