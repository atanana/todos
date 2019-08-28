package atanana.com.todoapp.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections

abstract class TodosViewModel : ViewModel() {
    private val navigationData = MutableLiveData<NavDirections>()
    val navigation: LiveData<NavDirections> = navigationData

    fun navigateTo(direction: NavDirections) {
        navigationData.value = direction
    }
}