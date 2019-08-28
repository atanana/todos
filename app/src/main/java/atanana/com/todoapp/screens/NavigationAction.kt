package atanana.com.todoapp.screens

import androidx.navigation.NavDirections

sealed class NavigationAction {
    object GoBack : NavigationAction()
    data class GoTo(val directions: NavDirections) : NavigationAction()
}