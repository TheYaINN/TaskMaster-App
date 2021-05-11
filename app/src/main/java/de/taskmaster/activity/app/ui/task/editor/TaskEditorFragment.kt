package de.taskmaster.activity.app.ui.task.editor

import android.app.Application
import android.os.Bundle
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import de.taskmaster.R
import de.taskmaster.activity.util.fragment.SubFragment
import de.taskmaster.databinding.FragmentTasksEditorBinding
import de.taskmaster.db.LocalDataBaseConnector
import de.taskmaster.model.data.impl.Displayable
import de.taskmaster.model.data.impl.Task
import de.taskmaster.model.handler.NavigationHandler
import de.taskmaster.model.handler.ToggleEditableComponentHandler
import kotlinx.coroutines.runBlocking

class TaskEditorFragment : SubFragment<FragmentTasksEditorBinding>(R.layout.fragment_tasks_editor) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val id = arguments?.getInt("id")
        if (id != null) {
            val application = requireActivity().application
            binder.model = ViewModelProvider(this, TaskEditorViewModelFactory(application, id)).get(TaskEditorViewModel::class.java)
        } else {
            binder.model = ViewModelProvider(this).get(TaskEditorViewModel::class.java)
        }
        binder.presenter = ToggleEditableComponentHandler(requireContext())
        binder.handler = NavigationHandler(this)

    }

}

class TaskEditorViewModel(id: Int) : Displayable() {

    var task: LiveData<Task> = runBlocking {
        return@runBlocking LocalDataBaseConnector.instance.taskDAO.getByID(id)
    }
}

class TaskEditorViewModelFactory(application: Application, val id: Int) : ViewModelProvider.AndroidViewModelFactory(application) {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TaskEditorViewModel(id) as T
    }

}