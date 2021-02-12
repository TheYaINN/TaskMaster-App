package de.taskmaster.activity.app.ui.task.editor

import android.os.Bundle
import android.view.View
import de.taskmaster.R
import de.taskmaster.activity.util.fragment.SubFragment
import de.taskmaster.databinding.FragmentTasksEditorBinding
import de.taskmaster.model.data.Task
import de.taskmaster.model.handler.ToggleEditableComponentHandler

class TaskEditorFragment : SubFragment<FragmentTasksEditorBinding>(R.layout.fragment_tasks_editor) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binder.model = Task()
        binder.presenter = ToggleEditableComponentHandler(requireContext())
    }

}