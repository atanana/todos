package atanana.com.todoapp.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import atanana.com.todoapp.screens.NavigationAction.GoBack
import atanana.com.todoapp.screens.NavigationAction.GoTo

abstract class TodosFragment<V : TodosViewModel> : Fragment() {
    protected abstract val model: V

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        model.navigation.observe(viewLifecycleOwner, Observer { action ->
            when (action) {
                GoBack -> findNavController().navigateUp()
                is GoTo -> findNavController().navigate(action.directions)
            }
        })
    }
}