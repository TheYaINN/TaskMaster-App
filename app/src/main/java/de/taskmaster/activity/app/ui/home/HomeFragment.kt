package de.taskmaster.activity.app.ui.home

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import de.taskmaster.R
import de.taskmaster.activity.util.BasicAdapter
import de.taskmaster.activity.util.fragment.TopLevelFragment
import de.taskmaster.db.LocalDataBaseConnector
import de.taskmaster.model.data.impl.TodoListWithAssociations
import de.taskmaster.model.toggleVisibility
import kotlinx.coroutines.launch

class HomeFragment : TopLevelFragment(R.layout.fragment_home, null) {

    private lateinit var viewModel: HomeViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this, HomeViewModelFactory(requireActivity().application, userId, viewLifecycleOwner))
            .get(HomeViewModel::class.java)

        val recyclerview = view.findViewById<RecyclerView>(R.id.calendar_events)
        val adapter = HomeAdapter(this)
        recyclerview.adapter = adapter
        viewModel.lists.observe(viewLifecycleOwner, { adapter.setData(it) })
    }
}

class HomeViewModel(userId: Int, viewLifecycleOwner: LifecycleOwner) : ViewModel() {
    private val _lists = MutableLiveData<List<TodoListWithAssociations>>()
    val lists: LiveData<List<TodoListWithAssociations>> = _lists

    init {
        viewModelScope.launch {
            LocalDataBaseConnector
                .instance
                .todoListWithAssociationsDAO
                .getByUserId(userId)
                .observe(viewLifecycleOwner, { _lists.postValue(it) })
        }
    }
}

class HomeViewModelFactory(application: Application, private val userId: Int, private val viewLifecycleOwner: LifecycleOwner) :
    ViewModelProvider.AndroidViewModelFactory(application) {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(userId, viewLifecycleOwner) as T
    }

}

class HomeAdapter(val fragment: Fragment) : BasicAdapter<TodoListWithAssociations, HomeAdapter.HomeViewHolder>() {

    private lateinit var listView: CardView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        listView = LayoutInflater.from(parent.context).inflate(R.layout.component_home_list, parent, false) as CardView
        return HomeViewHolder(listView)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(data[position])
    }

    inner class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(taskList: TodoListWithAssociations) {
            val title = itemView.findViewById<TextView>(R.id.title)
            val linearLayout = itemView.findViewById<LinearLayout>(R.id.items)

            title.text = taskList.list.title
            title.setOnClickListener {
                linearLayout.toggleVisibility()
            }

            addTasks(linearLayout, taskList)
        }

        private fun addTasks(linearLayout: LinearLayout, taskList: TodoListWithAssociations) {
            if (taskList.tasks.isEmpty()) {
                linearLayout.addView(createTextView(taskList.list.title))
            } else {
                taskList.tasks.forEach { linearLayout.addView(createTextView(it.title)) }
            }
        }

        private fun createTextView(text: String): TextView {
            return TextView(fragment.requireContext()).apply { this.text = text }
        }
    }
}
