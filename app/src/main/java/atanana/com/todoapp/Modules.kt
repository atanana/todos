package atanana.com.todoapp

import atanana.com.todoapp.data.Repository
import atanana.com.todoapp.screens.edittodo.EditTodoViewModel
import atanana.com.todoapp.screens.todos.TodosListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { buildDatabase(get()) }
    single { Repository(get()) }

    viewModel { TodosListViewModel(get(), get()) }
    viewModel { EditTodoViewModel(get(), get()) }
}