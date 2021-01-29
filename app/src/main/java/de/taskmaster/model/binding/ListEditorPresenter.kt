package de.taskmaster.model.binding

import android.content.Context
import android.view.View
import android.widget.TextView
import de.taskmaster.R

class ListEditorPresenter(val view: View, val context: Context) {

    fun toggleView(view: View) {
        val edit = (view as TextView)
        val lowerLayout = ((view.parent as View).parent as View).findViewById<View>(resolveID(view.id))
        lowerLayout.visibility = when (lowerLayout.visibility) {
            View.GONE -> {
                edit.text = context.getString(R.string.select)
                View.VISIBLE
            }
            View.VISIBLE -> {
                edit.text = context.getString(R.string.edit)
                View.GONE
            }
            else -> error("not supposed to happen")
        }
    }

    private fun resolveID(id: Int): Int {
        return when (id) {
            R.id.edit_deadline -> R.id.lower_deadline
            R.id.edit_group -> R.id.lower_group
            R.id.edit_repeating -> R.id.lower_repeating
            R.id.edit_place -> R.id.lower_place
            else -> error("ID could not be resolved")
        }
    }

}