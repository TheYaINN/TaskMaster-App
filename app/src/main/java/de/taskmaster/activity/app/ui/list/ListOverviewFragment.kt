package de.taskmaster.activity.app.ui.list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import de.taskmaster.R
import de.taskmaster.activity.util.fragment.TopLevelFragment
import de.taskmaster.model.data.TaskList
import de.taskmaster.model.data.User

class ListOverviewFragment : TopLevelFragment(R.layout.fragment_lists_overview) {

    private lateinit var viewModel: User

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<FloatingActionButton>(R.id.add_item).setOnClickListener {
            findNavController().navigate(R.id.action_navigation_list_to_listEditorFragment)
        }

        viewModel = ViewModelProvider(this).get(User::class.java)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = ListOverviewAdapter(this)
        adapter.setData(viewModel.lists)
        recyclerView.adapter = adapter
    }

    fun delete(taskList: TaskList) {
        val lists = viewModel.lists.toMutableList()
        lists.remove(taskList)
        viewModel.lists = lists
    }
}