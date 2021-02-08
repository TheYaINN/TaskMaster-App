package de.taskmaster.model.binding

import android.content.Context
import android.view.View
import android.widget.TextView
import de.taskmaster.R

class ToggleEditableComponentHandler(val context: Context) {

    val home = "Private Addresse"
    val work = "Arbeits Addresse"

    fun toggleView(view: View) {
        val edit = (view as TextView)
        val lowerLayout = ((view.parent as View).parent as View).findViewById<View>(R.id.editable_component_lower)
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

}