package de.taskmaster.ui.app.list.edit

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import de.taskmaster.R
import de.taskmaster.databinding.FragmentListEditBinding
import de.taskmaster.model.data.Group
import de.taskmaster.model.data.TaskList
import de.taskmaster.model.data.User
import de.taskmaster.model.handler.GroupSelector
import de.taskmaster.model.handler.ToggleEditableComponentHandler
import de.taskmaster.ui.app.SubFragment

class ListEditorFragment : SubFragment<FragmentListEditBinding>(R.layout.fragment_list_edit), GroupSelector {

    private val viewModel = ListEditorViewModel()
    private val smallGroupAdapter = SmallGroupAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binder.presenter = ToggleEditableComponentHandler(requireContext())
        binder.model = viewModel.model

        val recyclerView = binder.root.findViewById<RecyclerView>(R.id.group_items)
        recyclerView.adapter = smallGroupAdapter
        smallGroupAdapter.setData(viewModel.user.groups)
    }

    override fun selectGroup(group: Group) {
        viewModel.model.group = group
    }

    override fun save(): Boolean {
        print("SAVING: ${viewModel.model}")
        return super.save()
    }

}

class ListEditorViewModel {

    val model = TaskList()
    val user = User()


}
