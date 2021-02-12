package de.taskmaster.activity.app.ui.task

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import de.taskmaster.R
import de.taskmaster.activity.util.fragment.SubFragment
import de.taskmaster.databinding.FragmentTasksOverviewBinding
import de.taskmaster.model.data.Status
import de.taskmaster.model.data.Task
import de.taskmaster.model.data.User
import de.taskmaster.model.handler.NavigationHandler

class TaskOverview : SubFragment<FragmentTasksOverviewBinding>(R.layout.fragment_tasks_overview, null) {

    private lateinit var viewModel: User
    private lateinit var observableViewModel: MutableLiveData<List<Task>>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(User::class.java)
        observableViewModel = MutableLiveData(viewModel.lists[0].tasks)

        binder.handler = NavigationHandler(this)

        val recyclerView = view.findViewById<RecyclerView>(R.id.items)
        val adapter = TaskAdapter(this)
        recyclerView.adapter = adapter

        observableViewModel.observe(viewLifecycleOwner, adapter::setData)
        observableViewModel.observe(viewLifecycleOwner,
            { list ->
                view.findViewById<TextView>(R.id.list_status).text =
                    getString(R.string.tasks_done, list.filter { it.status == Status.FINISHED }.size, list.size)
            })
    }

}