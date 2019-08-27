package atanana.com.todoapp.screens

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren

abstract class TodosFragment : androidx.fragment.app.Fragment() {
    var job = SupervisorJob()
    protected val uiScope = CoroutineScope(Dispatchers.Main + job)

    open fun onBackPressed() {}

    override fun onPause() {
        super.onPause()
        uiScope.coroutineContext.cancelChildren()
    }
}