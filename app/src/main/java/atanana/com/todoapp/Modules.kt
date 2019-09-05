package atanana.com.todoapp

import atanana.com.todoapp.data.Repository
import atanana.com.todoapp.screens.edittodo.EditTodoViewModel
import atanana.com.todoapp.screens.todos.TodosListViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { buildDatabase(get()) }
    single { Repository(Firebase.firestore) }

    viewModel { TodosListViewModel(get(), get()) }
    viewModel { EditTodoViewModel(get(), get()) }
}