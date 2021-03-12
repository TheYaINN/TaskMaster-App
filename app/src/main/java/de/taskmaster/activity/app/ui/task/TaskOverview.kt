package de.taskmaster.activity.app.ui.task

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import de.taskmaster.R
import de.taskmaster.activity.util.fragment.SubFragment
import de.taskmaster.databinding.FragmentTasksOverviewBinding
import de.taskmaster.model.data.impl.Status
import de.taskmaster.model.data.impl.Task
import de.taskmaster.model.handler.NavigationHandler

class TaskOverview : SubFragment<FragmentTasksOverviewBinding>(R.layout.fragment_tasks_overview, null) {

    private lateinit var viewModel: TaskViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        binder.handler = NavigationHandler(this)

        val recyclerView = view.findViewById<RecyclerView>(R.id.items)
        val adapter = TaskAdapter(this)
        recyclerView.adapter = adapter

        viewModel.tasks.observe(viewLifecycleOwner,
            { list ->
                adapter.setData(list)
                view.findViewById<TextView>(R.id.list_status).text =
                    getString(R.string.tasks_done, list.filter { it.status == Status.FINISHED }.size, list.size)
            })
    }

}

class TaskViewModel : ViewModel() {
    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>> = _tasks

    init {
        //TODO: remove only for testing purpose
        val arr = arrayListOf<Task>()
        for (i in 0 until 10) {
            arr.add(Task(0, null, "Test 1", "Test 1 desc", Status.OPEN, null))
        }
        _tasks.postValue(arr)
    }
}
