package atanana.com.todoapp

import atanana.com.todoapp.screens.edittodo.EditTodoViewModel
import atanana.com.todoapp.screens.todos.TodosListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

//fun activityModule(activity: androidx.fragment.app.FragmentActivity): Kodein.Module =
//    Kodein.Module("Activity module") {
//        bind<FragmentManager>() with singleton { activity.supportFragmentManager }
//        bind() from scoped(AndroidLifecycleScope).singleton {
//            TodosListPresenter(instance(), instance())
//        }
//    }
//
//fun appModule(): Kodein.Module = Kodein.Module("App module") {
//    bind() from singleton { buildDatabase(instance()) }
//    bind() from factory { view: EditTodo -> EditTodoPresenter(instance(), view) }
//}

val appModule = module {
    single { buildDatabase(get()) }

    viewModel { TodosListViewModel(get()) }
    viewModel { EditTodoViewModel(get()) }
}