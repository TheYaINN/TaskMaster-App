package de.taskmaster.activity.app.ui.task

import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import de.taskmaster.R
import de.taskmaster.activity.util.fragment.SubFragment
import de.taskmaster.databinding.FragmentTasksOverviewBinding
import de.taskmaster.model.data.Task
import de.taskmaster.model.data.User

class TaskOverview : SubFragment<FragmentTasksOverviewBinding>(R.layout.fragment_tasks_overview, null) {

    private lateinit var viewModel: User
    private lateinit var observableLists: MutableLiveData<List<Task>>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(User::class.java)
        observableLists = MutableLiveData(viewModel.lists[0].tasks)

        val recyclerView = view.findViewById<RecyclerView>(R.id.items)

        val adapter = TaskAdapter(this)
        observableLists.observe(viewLifecycleOwner, adapter::setData)
        recyclerView.adapter = adapter
    }

}