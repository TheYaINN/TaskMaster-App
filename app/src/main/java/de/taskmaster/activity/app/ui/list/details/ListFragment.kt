package de.taskmaster.activity.app.ui.list.details

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import de.taskmaster.R
import de.taskmaster.activity.util.SubFragment
import de.taskmaster.databinding.FragmentListDetailsBinding
import de.taskmaster.model.data.User

class ListFragment : SubFragment<FragmentListDetailsBinding>(R.layout.fragment_list_details, null) {

    private lateinit var viewModel: User

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(User::class.java)

        val recyclerView = view.findViewById<RecyclerView>(R.id.items)

        val adapter = TaskAdapter(this)
        adapter.setData(viewModel.lists[0].tasks!!)
        recyclerView.adapter = adapter
    }

}