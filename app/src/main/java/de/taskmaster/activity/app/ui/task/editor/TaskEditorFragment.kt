package de.taskmaster.activity.app.ui.task.editor

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import de.taskmaster.R
import de.taskmaster.activity.util.fragment.SubFragment
import de.taskmaster.databinding.FragmentTasksEditorBinding
import de.taskmaster.model.data.impl.Displayable
import de.taskmaster.model.data.impl.ObservableViewModel
import de.taskmaster.model.data.impl.User
import de.taskmaster.model.handler.NavigationHandler
import de.taskmaster.model.handler.ToggleEditableComponentHandler

class TaskEditorFragment : SubFragment<FragmentTasksEditorBinding>(R.layout.fragment_tasks_editor) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binder.model = ViewModelProvider(this).get(TaskEditorViewModel::class.java)
        binder.presenter = ToggleEditableComponentHandler(requireContext())
        binder.handler = NavigationHandler(this)
    }

}

class TaskEditorViewModel : ObservableViewModel(), Displayable {


    val responsiblePerson: User? = null

    override fun getImage(): Bitmap? {
        TODO("Not yet implemented")
    }

    override fun rotate() {
        TODO("Not yet implemented")
    }

}