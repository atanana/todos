package atanana.com.todoapp.screens

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein

abstract class TodosViewModel(app: Application) : AndroidViewModel(app), KodeinAware {
    override val kodein by kodein()
}