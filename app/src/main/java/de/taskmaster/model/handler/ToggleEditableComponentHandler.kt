package de.taskmaster.model.handler

import android.content.Context
import android.view.View
import android.widget.TextView
import de.taskmaster.R

open class ToggleEditableComponentHandler(
    val context: Context,
    private val hiddenText: String = context.getString(R.string.edit),
    private val visibleText: String = context.getString(R.string.select),
) {


    fun toggleView(view: View) {
        val edit = (view as TextView)
        val lowerLayout = ((view.parent as View).parent as View).findViewById<View>(R.id.editable_component_lower)
        lowerLayout.visibility = when (lowerLayout.visibility) {
            View.GONE -> {
                edit.text = visibleText
                View.VISIBLE
            }
            else -> {
                edit.text = hiddenText
                View.GONE
            }
        }
    }

}