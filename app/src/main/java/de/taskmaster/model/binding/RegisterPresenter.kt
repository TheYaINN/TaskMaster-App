package de.taskmaster.model.binding

import android.view.View
import android.widget.LinearLayout
import de.taskmaster.R

class RegisterPresenter {

    val home = "Private Addresse"
    val work = "Arbeits Addresse"

    fun toggleView(view: View) {
        val lower = ((view.parent as View).parent as View).findViewById<LinearLayout>(R.id.lower_address)
        lower.visibility = when (lower.visibility) {
            View.GONE -> View.VISIBLE
            View.VISIBLE -> View.GONE
            else -> View.GONE
        }
    }
}