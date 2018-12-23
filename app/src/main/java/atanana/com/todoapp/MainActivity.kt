package atanana.com.todoapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.container)
        if (currentFragment == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, TodosList.newInstance())
                .commit()
        }
    }
}
