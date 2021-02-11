package de.taskmaster.activity.app.ui.home

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import de.taskmaster.R
import de.taskmaster.activity.util.TopLevelFragment
import de.taskmaster.model.data.User

class HomeFragment : TopLevelFragment(R.layout.fragment_home, null) {

    private lateinit var viewModel: User

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(User::class.java)
        val layout = view.findViewById<LinearLayout>(R.id.calendar_events)
        super.onViewCreated(view, savedInstanceState)
    }

}