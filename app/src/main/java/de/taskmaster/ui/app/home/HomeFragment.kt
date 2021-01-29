package de.taskmaster.ui.app.home

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import de.taskmaster.R
import de.taskmaster.ui.app.TopLevelFragment
import de.taskmaster.ui.elements.calendar.DateTextView

class HomeFragment : TopLevelFragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<LinearLayout>(R.id.calendar_events).apply {
            for (i in 0 until 10)
                addView(DateTextView(requireContext()))
        }
    }
}