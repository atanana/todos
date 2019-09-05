package atanana.com.todoapp.data

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.tasks.await

class Repository(private val firestore: FirebaseFirestore) {
    private val todos: CollectionReference
        get() = firestore.collection("todos")

    suspend fun allTodos(): List<Todo> =
        todos.get().await().mapNotNull { it.toTodo() }

    suspend fun update(todo: Todo) {
        if (todo.id == NEW_TODO) {
            todos.add(todo).await()
        } else {
            val data = mapOf(
                "title" to todo.title,
                "text" to todo.text
            )
            todos.document(todo.id).update(data)
        }
    }

    suspend fun byId(id: String): Todo? {
        return todos.document(id).get().await().toTodo()
    }

    private fun DocumentSnapshot.toTodo() = toObject<Todo>()?.copy(id = id)

    companion object {
        const val NEW_TODO = ""
    }
}