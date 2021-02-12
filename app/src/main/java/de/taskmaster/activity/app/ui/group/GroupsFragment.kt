package de.taskmaster.activity.app.ui.group

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import de.taskmaster.R
import de.taskmaster.activity.util.fragment.TopLevelFragment
import de.taskmaster.model.data.User

class GroupsFragment : TopLevelFragment(R.layout.fragment_group, R.menu.lists_groups_menu) {

    private lateinit var viewModel: User

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<FloatingActionButton>(R.id.add_item).setOnClickListener {
            findNavController().navigate(R.id.action_navigation_group_to_groupEditorFragment)
        }

        viewModel = ViewModelProvider(this).get(User::class.java)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = BigGroupAdapter(this)
        recyclerView.adapter = adapter
        adapter.setData(viewModel.groups)
    }

}