package de.taskmaster.activity.app.ui.task.editor

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import de.taskmaster.R
import de.taskmaster.activity.util.fragment.SubFragment
import de.taskmaster.databinding.FragmentTasksEditorBinding
import de.taskmaster.model.data.impl.Displayable
import de.taskmaster.model.data.impl.User
import de.taskmaster.model.handler.NavigationHandler
import de.taskmaster.model.handler.ToggleEditableComponentHandler
import kotlinx.coroutines.launch

class TaskEditorFragment : SubFragment<FragmentTasksEditorBinding>(R.layout.fragment_tasks_editor) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binder.model = ViewModelProvider(this).get(TaskEditorViewModel::class.java)
        binder.presenter = ToggleEditableComponentHandler(requireContext())
        binder.handler = NavigationHandler(this)
    }

}

class TaskEditorViewModel : Displayable() {

    var title: String = ""
    var description: String = ""
    var responsiblePerson: User? = null

    init {
        viewModelScope.launch {
            //TODO: remove only for testing
            title = "Test title"
            description = "Test Description"
            responsiblePerson = User(0, null, "Test", "pwd", "", 100, "Bengt", "Joachimsohn", "bengt@joachimsohn.de")
        }
    }

}