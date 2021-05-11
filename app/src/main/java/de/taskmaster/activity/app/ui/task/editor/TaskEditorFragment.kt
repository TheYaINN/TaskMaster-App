package de.taskmaster.activity.app.ui.task.editor

import android.os.Bundle
import android.view.View
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

    lateinit var viewModel: TaskEditorViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val listId = arguments?.getInt("listId")!!
        val taskId = arguments?.getInt("taskId")

        binder.model = ViewModelProvider(this).get(
            TaskEditorViewModel::class.java
        )
        binder.presenter = ToggleEditableComponentHandler(requireContext())
        binder.handler = NavigationHandler(this)

        viewModel = ViewModelProvider(this).get(TaskEditorViewModel::class.java)

    }

    override fun save(): Boolean {
        GlobalScope.launch {
            val task = viewModel.build()
            task.listId = arguments?.getInt("listId")!!
            if (false) {
                LocalDataBaseConnector.instance.taskDAO.update(task)
            } else {
                LocalDataBaseConnector.instance.taskDAO.insert(task)
            }

        }
        return super.save()
    }

}

class TaskEditorViewModel() : Displayable() {

    var title = ""
    var description = ""
    var responsiblePerson: User? = null

    fun build(): Task {

        return Task(
            taskId = 0,
            title = title,
            description = description,
            status = Status.OPEN
        )
    }

}