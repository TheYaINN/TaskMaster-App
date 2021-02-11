package de.taskmaster.activity.app.ui.group

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import de.taskmaster.R
import de.taskmaster.activity.util.TopLevelFragment
import de.taskmaster.model.model.observable.GroupObservable

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