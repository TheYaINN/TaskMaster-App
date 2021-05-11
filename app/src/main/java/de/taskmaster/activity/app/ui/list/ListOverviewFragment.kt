package de.taskmaster.activity.app.ui.list

import android.app.Application
import android.os.Bundle
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import de.taskmaster.R
import de.taskmaster.activity.util.fragment.TopLevelFragment
import de.taskmaster.db.LocalDataBaseConnector
import de.taskmaster.model.data.impl.TodoListWithAssociations
import kotlinx.coroutines.launch

class ListOverviewFragment : TopLevelFragment(R.layout.fragment_lists_overview) {

    private lateinit var viewModel: ListOverviewViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<FloatingActionButton>(R.id.add_item).setOnClickListener {
            findNavController().navigate(R.id.action_navigation_list_to_listEditorFragment)
        }

        viewModel = ViewModelProvider(this, ListOverviewViewModelFactory(requireActivity().application, userId, viewLifecycleOwner))
            .get(ListOverviewViewModel::class.java)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = ListOverviewAdapter(this)
        recyclerView.adapter = adapter
        viewModel.lists.observe(viewLifecycleOwner, { adapter.setData(it) })
    }
}

class ListOverviewViewModel(userId: Int, viewLifecycleOwner: LifecycleOwner) : ViewModel() {

    private val _lists = MutableLiveData<List<TodoListWithAssociations>>()
    val lists: LiveData<List<TodoListWithAssociations>> = _lists

    init {
        viewModelScope.launch {
            LocalDataBaseConnector.instance.todoListWithAssociationsDAO.getByUserId(userId)
                .observe(viewLifecycleOwner, { _lists.postValue(it) })
        }
    }
}

class ListOverviewViewModelFactory(
    application: Application,
    private val userId: Int,
    private val viewLifecycleOwner: LifecycleOwner,
) :
    ViewModelProvider.AndroidViewModelFactory(application) {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ListOverviewViewModel(userId, viewLifecycleOwner) as T
    }

}

