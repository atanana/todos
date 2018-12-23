package atanana.com.todoapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import atanana.com.todoapp.screens.todos.TodosList

class MainActivity : AppCompatActivity() {

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
