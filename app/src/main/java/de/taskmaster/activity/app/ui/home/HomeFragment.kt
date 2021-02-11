package de.taskmaster.activity.app.ui.home

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import de.taskmaster.R
import de.taskmaster.activity.util.TopLevelFragment
import de.taskmaster.model.data.TaskList
import de.taskmaster.model.model.viewmodel.ListViewModel

class HomeFragment : TopLevelFragment(R.layout.fragment_home, null) {

    private lateinit var viewModel: ListViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        val layout = view.findViewById<LinearLayout>(R.id.calendar_events)
        viewModel.lists.observe(viewLifecycleOwner) { lists -> lists.forEach { list -> layout.addView(ListView(list, requireContext())) } }
        super.onViewCreated(view, savedInstanceState)
    }

}

class ListView(list: TaskList, context: Context) : LinearLayout(context) {

    init {
        //TODO: think of a design
        addView(TextView(context).apply {
            text = list.title
        })
    }

}

