package de.taskmaster.activity.app.ui.task

import android.app.Application
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import de.taskmaster.R
import de.taskmaster.activity.util.fragment.SubFragment
import de.taskmaster.databinding.FragmentTasksOverviewBinding
import de.taskmaster.db.LocalDataBaseConnector
import de.taskmaster.model.data.impl.Status
import de.taskmaster.model.data.impl.Task
import kotlinx.coroutines.launch

class TaskOverview :
    SubFragment<FragmentTasksOverviewBinding>(R.layout.fragment_tasks_overview, null) {

    private lateinit var viewModel: TaskViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val listId = arguments?.getInt("id")!!
        viewModel = ViewModelProvider(
            this,
            TaskViewModelFactory(requireActivity().application, listId, viewLifecycleOwner)
        ).get(TaskViewModel::class.java)


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
                    getString(
                        R.string.tasks_done,
                        list.filter { it.status == Status.FINISHED }.size,
                        list.size
                    )
            })
    }

}

class TaskViewModel(listId: Int, viewLifecycleOwner: LifecycleOwner) : ViewModel() {

    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>> = _tasks

    init {
        viewModelScope.launch {
            LocalDataBaseConnector.instance.taskDAO.getByListId(listId)
                .observe(viewLifecycleOwner, { _tasks.postValue(it) })
        }
    }
}

class TaskViewModelFactory(
    application: Application,
    private val listId: Int,
    private val viewLifecycleOwner: LifecycleOwner
) :
    ViewModelProvider.AndroidViewModelFactory(application) {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TaskViewModel(listId, viewLifecycleOwner) as T
    }

}
