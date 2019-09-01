package atanana.com.todoapp.screens

import android.content.Intent
import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import atanana.com.todoapp.screens.NavigationAction.GoBack
import atanana.com.todoapp.screens.NavigationAction.GoTo
import com.hadilq.liveevent.LiveEvent

abstract class TodosViewModel : ViewModel() {
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

    open fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {}
}