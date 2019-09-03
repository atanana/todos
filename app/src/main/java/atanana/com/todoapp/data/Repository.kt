package atanana.com.todoapp.data

import atanana.com.todoapp.db.TodoEntity
import atanana.com.todoapp.db.TodosDatabase
import atanana.com.todoapp.db.toEntity
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.coroutines.suspendCoroutine

class Repository(database: TodosDatabase) {
    private val todoDao = database.todosDao()

    suspend fun allTodos(): List<Todo> = todoDao.allTodos()
        .map(TodoEntity::toTodo)

    suspend fun add(todo: Todo) {
        todoDao.insert(todo.toEntity())
        Firebase.firestore.collection("todos")
            .add(todo)
            .await()
    }

    suspend fun byId(id: Long): Todo? = todoDao.byId(id)?.toTodo()
}

suspend fun Task<DocumentReference>.await() = suspendCoroutine<Unit> { continuation ->
    addOnSuccessListener { continuation.resumeWith(Result.success(Unit)) }
    addOnFailureListener { error -> continuation.resumeWith(Result.failure(error)) }
}