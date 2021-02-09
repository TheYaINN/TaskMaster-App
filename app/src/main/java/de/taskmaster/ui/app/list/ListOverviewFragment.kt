package de.taskmaster.ui.app.list

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

class ListOverviewFragment : TopLevelFragment(R.layout.fragment_list) {

    private lateinit var viewModelOverview: ListOverviewFragmentViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModelOverview = ViewModelProvider(this).get(ListOverviewFragmentViewModel::class.java)
        view.findViewById<FloatingActionButton>(R.id.add_item).setOnClickListener {
            findNavController().navigate(R.id.action_navigation_list_to_listEditorFragment)
        }
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)

        val listAdapter = ListOverviewAdapter(this)
        viewModelOverview.lists.observe(requireActivity(), listAdapter::setData)
        recyclerView.adapter = listAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.lists_groups_menu, menu)
    }
}