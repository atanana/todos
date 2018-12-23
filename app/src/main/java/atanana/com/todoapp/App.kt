package atanana.com.todoapp

import android.app.Application
import atanana.com.todoapp.screens.todos.TodosListPresenter
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.androidModule
import org.kodein.di.android.support.AndroidLifecycleScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton

class App : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidModule(this@App))

        bind() from scoped(AndroidLifecycleScope).singleton { TodosListPresenter(instance()) }
    }
}