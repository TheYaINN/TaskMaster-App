package de.taskmaster.ui.elements.calendar

import android.content.Context
import android.widget.LinearLayout
import android.widget.TextView
import java.time.LocalDate

class DateTextView(context: Context) : LinearLayout(context) {

    private val events: MutableList<EventTextView> = arrayListOf()

    //TODO: design this better

    init {
        orientation = VERTICAL
        val title = TextView(context)
        title.text = "${LocalDate.now()}"
        title.textSize = 16f
        title.setPadding(0, 0, 0, 0)
        events.add(EventTextView(context))
        events.add(EventTextView(context))
        events.add(EventTextView(context))
        events.add(EventTextView(context))
        addView(title)
        events.forEach { addView(it) }
    }

}