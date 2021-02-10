package de.taskmaster.ui.app.list.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import de.taskmaster.R
import de.taskmaster.databinding.FragmentListEditBinding
import de.taskmaster.model.data.Group
import de.taskmaster.model.handler.GroupSelector
import de.taskmaster.model.handler.ToggleEditableComponentHandler
import de.taskmaster.ui.app.SubFragment

class ListEditorFragment : SubFragment(R.layout.fragment_list_edit), GroupSelector {

    private val listEditorViewModel = ListEditorViewModel()
    private val smallGroupAdapter = SmallGroupAdapter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val fragmentBinding = DataBindingUtil.inflate<FragmentListEditBinding>(inflater, R.layout.fragment_list_edit, container, false)
        fragmentBinding.presenter = ToggleEditableComponentHandler(requireContext())
        fragmentBinding.model = listEditorViewModel
        val recyclerView = fragmentBinding.root.findViewById<RecyclerView>(R.id.group_items)
        recyclerView.adapter = smallGroupAdapter
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        smallGroupAdapter.setData(arrayListOf(Group().also { group -> group.title = "test" },
            Group().also { group -> group.title = "tes2" },
            Group().also { group -> group.title = "tes123t" },
            Group().also { group -> group.title = "te123123213st" }))
    }

    override fun selectGroup(group: Group) {
        listEditorViewModel.setGroup(group)
    }

}