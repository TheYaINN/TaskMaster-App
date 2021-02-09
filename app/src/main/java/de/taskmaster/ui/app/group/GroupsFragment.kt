package de.taskmaster.ui.app.group

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import de.taskmaster.R
import de.taskmaster.ui.app.TopLevelFragment
import de.taskmaster.ui.app.group.editor.GroupsEditorViewModel

class GroupsFragment : TopLevelFragment(R.layout.fragment_group) {

    private lateinit var groupsViewModel: GroupsEditorViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        groupsViewModel = ViewModelProvider(this).get(GroupsEditorViewModel::class.java)
        view.findViewById<FloatingActionButton>(R.id.add_item).setOnClickListener {
            findNavController().navigate(R.id.action_navigation_group_to_groupEditorFragment)
        }
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.adapter = GroupAdapter(this)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.lists_groups_menu, menu)
    }
}