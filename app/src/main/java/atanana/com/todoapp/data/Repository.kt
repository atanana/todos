package atanana.com.todoapp.data

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.tasks.await

class Repository(private val firestore: FirebaseFirestore) {
    private val todos: CollectionReference
        get() = firestore.collection("todos")

    suspend fun allTodos(): List<Todo> =
        todos.get().await().toObjects()

    suspend fun add(todo: Todo) {
        todos.add(todo).await()
    }

    suspend fun byId(id: String): Todo? = todos.document(id).get().await().toObject<Todo>()
}