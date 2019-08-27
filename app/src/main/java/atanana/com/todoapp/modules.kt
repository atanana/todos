package atanana.com.todoapp

import atanana.com.todoapp.screens.edittodo.EditTodo
import atanana.com.todoapp.screens.edittodo.EditTodoPresenter
import atanana.com.todoapp.screens.todos.TodosListPresenter
import org.kodein.di.Kodein
import org.kodein.di.android.support.AndroidLifecycleScope
import org.kodein.di.generic.*

fun activityModule(activity: androidx.fragment.app.FragmentActivity): Kodein.Module =
    Kodein.Module("Activity module") {
        bind<androidx.fragment.app.FragmentManager>() with singleton { activity.supportFragmentManager }
    bind() from scoped(AndroidLifecycleScope).singleton { TodosListPresenter(instance(), instance()) }
}

fun appModule(app: App): Kodein.Module = Kodein.Module("App module") {
    bind() from singleton { buildDatabase(instance()) }
    bind() from factory { view: EditTodo -> EditTodoPresenter(instance(), view) }
}