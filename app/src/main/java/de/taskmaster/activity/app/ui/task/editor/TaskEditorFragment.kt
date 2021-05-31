package de.taskmaster.activity.app.ui.task.editor

import android.app.Application
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import de.taskmaster.R
import de.taskmaster.activity.util.fragment.SubFragment
import de.taskmaster.databinding.FragmentTasksEditorBinding
import de.taskmaster.db.LocalDataBaseConnector
import de.taskmaster.model.data.impl.Displayable
import de.taskmaster.model.data.impl.Status
import de.taskmaster.model.data.impl.Task
import de.taskmaster.model.data.impl.User
import de.taskmaster.model.handler.NavigationHandler
import de.taskmaster.model.handler.ToggleEditableComponentHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TaskEditorFragment : SubFragment<FragmentTasksEditorBinding>(R.layout.fragment_tasks_editor) {

    private var isEditMode = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val taskId = arguments?.getInt("taskId")
        isEditMode = taskId != null

        binder.model = ViewModelProvider(this, TaskEditorViewModelFactory(requireActivity().application, taskId ?: 0, isEditMode))
            .get(TaskEditorViewModel::class.java)
        binder.presenter = ToggleEditableComponentHandler(requireContext())
        binder.handler = NavigationHandler(this)
    }

    override fun save(): Boolean {
        GlobalScope.launch {
            val task = binder.model!!.build()
            if (isEditMode) {
                LocalDataBaseConnector.instance.taskDAO.update(task)
            } else {
                LocalDataBaseConnector.instance.taskDAO.insert(task)
            }
        }
        return super.save()
    }

}

class TaskEditorViewModel(private val taskId: Int, private val isEditMode: Boolean) : Displayable() {

    var title = ""
    var description = ""
    var responsiblePerson: User? = null

    fun build(): Task {
        val task = Task(title = title, description = description, status = Status.OPEN)
        return if (isEditMode) task.apply { task.taskId = this@TaskEditorViewModel.taskId } else task
    }

}

class TaskEditorViewModelFactory(
    application: Application,
    private val taskId: Int,
    private val isEditMode: Boolean,
) :
    ViewModelProvider.AndroidViewModelFactory(application) {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TaskEditorViewModel(taskId, isEditMode) as T
    }

}