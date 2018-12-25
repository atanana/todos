package atanana.com.todoapp

import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import atanana.com.todoapp.screens.edittodo.EditTodoPresenter
import atanana.com.todoapp.screens.todos.TodosListPresenter
import org.kodein.di.Kodein
import org.kodein.di.android.support.AndroidLifecycleScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton

fun activityModule(activity: FragmentActivity): Kodein.Module = Kodein.Module("Activity module") {
    bind<FragmentManager>() with singleton { activity.supportFragmentManager }
    bind() from scoped(AndroidLifecycleScope).singleton { TodosListPresenter(instance()) }
}

fun appModule(app: App): Kodein.Module = Kodein.Module("App module") {
    bind() from singleton { buildDatabase(instance()) }
    bind() from scoped(AndroidLifecycleScope).singleton { EditTodoPresenter(instance()) }
}