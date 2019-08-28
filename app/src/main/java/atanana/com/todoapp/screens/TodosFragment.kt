package atanana.com.todoapp.screens

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

abstract class TodosFragment<V : ViewModel> : Fragment() {
    protected abstract val model: V
}