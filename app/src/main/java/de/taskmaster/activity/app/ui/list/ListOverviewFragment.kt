package de.taskmaster.activity.app.ui.list

import android.app.Application
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import de.taskmaster.R
import de.taskmaster.activity.util.fragment.TopLevelFragment
import de.taskmaster.auth.LocalAuthHelper
import de.taskmaster.db.LocalDataBaseConnector
import de.taskmaster.model.data.impl.ToDoList
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ListOverviewFragment : TopLevelFragment(R.layout.fragment_lists_overview) {

    private lateinit var viewModel: ListOverviewViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<FloatingActionButton>(R.id.add_item).setOnClickListener {
            findNavController().navigate(R.id.action_navigation_list_to_listEditorFragment)
        }

        val application = activity?.application ?: error("Could not retrieve application")

        var userId = context?.getSharedPreferences(
            LocalAuthHelper.preferencesKey,
            AppCompatActivity.MODE_PRIVATE
        )?.getInt(LocalAuthHelper.useridKey, -1)

        if (userId != null) {
            viewModel = ViewModelProvider(
                this,
                ListOverviewViewModelFactory(application, userId)
            ).get(ListOverviewViewModel::class.java)
        }


        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = ListOverviewAdapter(this)
        recyclerView.adapter = adapter

        viewModel.lists.observe(viewLifecycleOwner){adapter::data}
    }

    fun delete(toDoList: ToDoList) {
        GlobalScope.async {
            LocalDataBaseConnector.instance.toDoListDAO.delete(toDoList)
        }
    }
}

class ListOverviewViewModel(userId: Int) : ViewModel() {

    private val _lists = MutableLiveData<List<ToDoList>>()
    val lists: LiveData<List<ToDoList>> = _lists

    init {
        viewModelScope.launch {
            LocalDataBaseConnector.instance.toDoListDAO.getByUserId(userId)
                .observeForever { _lists.postValue(it) }
        }
    }
}

class ListOverviewViewModelFactory(application: Application, val userId: Int) :
    ViewModelProvider.AndroidViewModelFactory(application) {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ListOverviewViewModel(userId) as T
    }

}

