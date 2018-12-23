package atanana.com.todoapp

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind

class App : Application(), KodeinAware {
    override val kodein = Kodein.lazy {

    }

    override fun onCreate() {
        super.onCreate()
    }
}