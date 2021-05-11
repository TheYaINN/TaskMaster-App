package de.taskmaster.activity.app.ui.task

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import de.taskmaster.R
import de.taskmaster.activity.util.fragment.SubFragment
import de.taskmaster.databinding.FragmentTasksOverviewBinding
import de.taskmaster.model.data.impl.Status
import de.taskmaster.model.data.impl.Task
import de.taskmaster.model.handler.NavigationHandler
import kotlinx.coroutines.launch

class TaskOverview : SubFragment<FragmentTasksOverviewBinding>(R.layout.fragment_tasks_overview, null) {

    private lateinit var viewModel: TaskViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        val listId = arguments?.getInt("id")!!

        val addButton = view.findViewById<FloatingActionButton>(R.id.add_item)
        addButton.setOnClickListener {
            val bundle = bundleOf("listId" to listId)
            findNavController().navigate(R.id.action_taskOverview_to_taskEditorFragment, bundle)
        }

        val recyclerView = view.findViewById<RecyclerView>(R.id.items)
        val adapter = TaskAdapter(this, listId)
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
        viewModelScope.launch {
            //Task
            val id = 1L
            //TODO: LocalDataBaseConnector.instance.taskDAO.getByID(id).observeForever { _tasks.postValue(it) }
        }
    }
}
