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
import de.taskmaster.model.rotate

class TaskEditorFragment : SubFragment<FragmentTasksEditorBinding>(R.layout.fragment_tasks_editor) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binder.model = ViewModelProvider(this).get(TaskEditorViewModel::class.java)
        binder.presenter = ToggleEditableComponentHandler(requireContext())
        binder.handler = NavigationHandler(this)
    }

}

class TaskEditorViewModel : ObservableViewModel(), Displayable {

    var img: Bitmap? = null
    var title: String = ""
    var description: String = ""

    var responsiblePerson: User? = null

    override fun getImage(): Bitmap? {
        return img
    }

    override fun rotate() {
        img?.rotate(90f)
    }

    init {
        //TODO: remove only for testing
        title = "Test title"
        description = "Test Description"
        responsiblePerson = User(0, null, "Test", "pwd", "", 100, "Bengt", "Joachimsohn", "bengt@joachimsohn.de")
    }

}