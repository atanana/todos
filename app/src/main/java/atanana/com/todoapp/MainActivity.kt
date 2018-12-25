package atanana.com.todoapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import atanana.com.todoapp.screens.TodosFragment
import atanana.com.todoapp.screens.todos.TodosList
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein

class MainActivity : AppCompatActivity(), KodeinAware {
    private val parentKodein by closestKodein()

    override val kodein: Kodein by lazy {
        Kodein {
            extend(parentKodein)
            import(activityModule(this@MainActivity))
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        val todosFragment = findCurrentFragment() as? TodosFragment
        todosFragment?.onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (findCurrentFragment() == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, TodosList.newInstance())
                .commit()
        }
    }

    private fun findCurrentFragment() = supportFragmentManager.findFragmentById(R.id.container)
}
