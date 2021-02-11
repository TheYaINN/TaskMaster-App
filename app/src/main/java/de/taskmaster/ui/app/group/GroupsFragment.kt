package de.taskmaster.ui.app.group

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import de.taskmaster.R
import de.taskmaster.model.model.observable.GroupObservable
import de.taskmaster.ui.app.TopLevelFragment

class GroupsFragment : TopLevelFragment(R.layout.fragment_group, R.menu.lists_groups_menu) {

    private lateinit var groupsViewModel: GroupObservable

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //FIXME: groupsViewModel = ViewModelProvider(this).get(GroupObservable::class.java)
        view.findViewById<FloatingActionButton>(R.id.add_item).setOnClickListener {
            findNavController().navigate(R.id.action_navigation_group_to_groupEditorFragment)
        }
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.adapter = BigGroupAdapter(this)
    }

}