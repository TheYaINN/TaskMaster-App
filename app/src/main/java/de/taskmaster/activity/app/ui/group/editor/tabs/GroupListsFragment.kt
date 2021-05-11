package de.taskmaster.activity.app.ui.group.editor.tabs

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import de.taskmaster.db.LocalDataBaseConnector
import de.taskmaster.model.data.impl.ToDoList
import kotlinx.coroutines.launch

class GroupListsFragment : Fragment(R.layout.fragment_lists_members) {

    private lateinit var viewModel: GroupListsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        //TODO get this from somewhere
        val groupId = 0
        viewModel = ViewModelProvider(this, GroupListsViewModelFactory(requireActivity().application, groupId, viewLifecycleOwner))
            .get(GroupListsViewModel::class.java)

        val adapter = GroupListAdapter(this)
        recyclerView.adapter = adapter
        viewModel.lists.observe(viewLifecycleOwner, {
            adapter.setData(it)
        })
    }
}

class GroupListsViewModel(groupId: Int, viewLifecycleOwner: LifecycleOwner) : ViewModel() {

    private val _lists: MutableLiveData<List<ToDoList>> = MutableLiveData()
    val lists: LiveData<List<ToDoList>> = _lists

    init {
        viewModelScope.launch {
            LocalDataBaseConnector.instance.groupWithTodDoListDao.getByGroupId(groupId).observe(viewLifecycleOwner, {
                if (it != null) {
                    _lists.postValue(it.list)
                }
            })
        }
    }
}

class GroupListsViewModelFactory(application: Application, private val groupId: Int, private val viewLifecycleOwner: LifecycleOwner) :
    ViewModelProvider.AndroidViewModelFactory(application) {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GroupListsViewModel(groupId, viewLifecycleOwner) as T
    }

}

class GroupListAdapter(val fragment: Fragment) : BasicAdapter<ToDoList, GroupListAdapter.GroupListViewHolder>() {

    private lateinit var listView: CardView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupListViewHolder {
        listView = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false) as CardView
        return GroupListViewHolder(listView)
    }

    override fun onBindViewHolder(holder: GroupListViewHolder, position: Int) {
        holder.bind(data[position], fragment)
    }

    class GroupListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(list: ToDoList, fragment: Fragment) {
            val name = itemView.findViewById<TextView>(R.id.item_title)
            val description = itemView.findViewById<TextView>(R.id.item_description)

            name.text = list.title
            description.text = list.description
        }

    }
}

