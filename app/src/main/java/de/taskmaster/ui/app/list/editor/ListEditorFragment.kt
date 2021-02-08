package de.taskmaster.ui.app.list.editor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import de.taskmaster.R
import de.taskmaster.databinding.FragmentListEditBinding
import de.taskmaster.model.binding.ToggleEditableComponentHandler
import de.taskmaster.model.data.TaskList
import de.taskmaster.ui.app.SubFragment

class ListEditorFragment : SubFragment(R.layout.fragment_list_edit) {

    private lateinit var taskList: TaskList

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val fragmentBinding = DataBindingUtil.inflate<FragmentListEditBinding>(inflater, R.layout.fragment_list_edit, container, false)
        fragmentBinding.presenter = ToggleEditableComponentHandler(requireContext())
        taskList = TaskList()
        fragmentBinding.model = taskList
        return fragmentBinding.root
    }

}