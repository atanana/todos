package atanana.com.todoapp.screens

import android.content.Intent
import androidx.navigation.NavDirections

sealed class NavigationAction {
    object GoBack : NavigationAction()
    data class GoTo(val directions: NavDirections) : NavigationAction()
    data class StartActivity(val intent: Intent, val requestCode: Int) : NavigationAction()
}