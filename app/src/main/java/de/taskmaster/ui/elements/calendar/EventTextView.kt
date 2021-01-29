package de.taskmaster.ui.elements.calendar

import android.content.Context
import androidx.appcompat.widget.AppCompatTextView

class EventTextView(context: Context) : AppCompatTextView(context) {

    init {
        text = "test"
        setPadding(8, 0, 0, 0)
    }

}