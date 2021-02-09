package de.taskmaster.model.binding

import android.content.Context
import android.view.View
import android.widget.TextView
import de.taskmaster.R

open class ToggleEditableComponentHandler(
    val context: Context,
    private val hiddenText: String = context.getString(R.string.select),
    private val visibleText: String = context.getString(R.string.edit),
) {


    fun toggleView(view: View) {
        val edit = (view as TextView)
        val lowerLayout = ((view.parent as View).parent as View).findViewById<View>(R.id.editable_component_lower)
        lowerLayout.visibility = when (lowerLayout.visibility) {
            View.GONE -> {
                edit.text = visibleText
                View.VISIBLE
            }
            View.VISIBLE -> {
                edit.text = hiddenText
                View.GONE
            }
            else -> error("not supposed to happen")
        }
    }

}