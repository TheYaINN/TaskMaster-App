package de.taskmaster.activity.app.ui.list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import de.taskmaster.R
import de.taskmaster.activity.util.fragment.TopLevelFragment
import de.taskmaster.db.LocalDataBaseConnector
import de.taskmaster.model.data.impl.ToDoList
import kotlinx.coroutines.launch

class ListOverviewFragment : TopLevelFragment(R.layout.fragment_lists_overview) {

    private lateinit var viewModel: ListOverviewViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<FloatingActionButton>(R.id.add_item).setOnClickListener {
            findNavController().navigate(R.id.action_navigation_list_to_listEditorFragment)
        }

        viewModel = ViewModelProvider(this).get(ListOverviewViewModel::class.java)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = ListOverviewAdapter(this)
        recyclerView.adapter = adapter

    }

    fun delete(taskList: ToDoList) {
        LocalDataBaseConnector.instance.delete(taskList)
    }
}

class ListOverviewViewModel : ViewModel() {

    private val _lists = MutableLiveData<ToDoList>()
    val lists: LiveData<ToDoList> = _lists

    init {
        viewModelScope.launch {
            LocalDataBaseConnector.instance.toDoListDAO.getByID(id).observeForever { _lists.postValue(it) }
        }
    }
}

