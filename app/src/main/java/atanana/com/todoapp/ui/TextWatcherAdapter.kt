package atanana.com.todoapp.ui

import android.text.Editable
import android.text.TextWatcher

class TextWatcherAdapter(private val onTextChanged: (text: String) -> Unit) : TextWatcher {
    override fun afterTextChanged(s: Editable?) {}

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        onTextChanged(s?.toString() ?: "")
    }
}