package atanana.com.todoapp.screens

import android.support.v4.app.Fragment

abstract class TodosFragment : Fragment() {
    open fun onBackPressed() {}
}