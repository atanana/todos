package atanana.com.todoapp.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import atanana.com.todoapp.screens.NavigationAction.GoBack
import atanana.com.todoapp.screens.NavigationAction.GoTo
import com.hadilq.liveevent.LiveEvent

abstract class TodosViewModel : ViewModel() {
    private val navigationData = LiveEvent<NavigationAction>()
    val navigation: LiveData<NavigationAction> = navigationData

    fun goTo(direction: NavDirections) {
        navigationData.postValue(GoTo(direction))
    }

    fun goBack() {
        navigationData.postValue(GoBack)
    }
}