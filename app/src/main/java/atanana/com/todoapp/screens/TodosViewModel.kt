package atanana.com.todoapp.screens

import android.app.Application
import android.content.Intent
import androidx.annotation.StringRes
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.navigation.NavDirections
import atanana.com.todoapp.screens.NavigationAction.*
import com.hadilq.liveevent.LiveEvent

abstract class TodosViewModel(protected val app: Application) : AndroidViewModel(app) {
    private val navigationData = LiveEvent<NavigationAction>()
    val navigation: LiveData<NavigationAction> = navigationData

    private val toastData = LiveEvent<Int>()
    val toast: LiveData<Int> = toastData

    fun goTo(direction: NavDirections) {
        navigationData.postValue(GoTo(direction))
    }

    fun goBack() {
        navigationData.postValue(GoBack)
    }

    fun showToast(@StringRes message: Int) {
        toastData.postValue(message)
    }

    fun startActivity(intent: Intent, requestCode: Int) {
        navigationData.postValue(StartActivity(intent, requestCode))
    }

    open fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {}
}