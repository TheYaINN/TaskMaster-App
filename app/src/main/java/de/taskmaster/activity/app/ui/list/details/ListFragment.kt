package de.taskmaster.activity.app.ui.list.details

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import de.taskmaster.R
import de.taskmaster.activity.util.SubFragment
import de.taskmaster.databinding.FragmentListDetailsBinding
import de.taskmaster.model.data.TaskList

class ListFragment : SubFragment<FragmentListDetailsBinding>(R.layout.fragment_list_details, null) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewModel = TaskList()

        val recyclerView = view.findViewById<RecyclerView>(R.id.items)

        val adapter = TaskAdapter()
        adapter.setData(viewModel.tasks)
        recyclerView.adapter = adapter
    }

}