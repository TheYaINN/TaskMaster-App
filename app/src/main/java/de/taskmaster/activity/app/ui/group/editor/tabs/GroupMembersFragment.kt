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
import androidx.recyclerview.widget.RecyclerView
import de.taskmaster.R
import de.taskmaster.activity.util.BasicAdapter
import de.taskmaster.model.data.impl.User
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GroupMembersFragment(private val groupId: Int?) : Fragment(R.layout.fragment_group_members) {

    private lateinit var viewModel: GroupMembersViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this, GroupMembersViewModelFactory(requireActivity().application, groupId, viewLifecycleOwner))
            .get(GroupMembersViewModel::class.java)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = GroupMemberAdapter()
        recyclerView.adapter = adapter
        viewModel.members.observe(viewLifecycleOwner, { adapter.setData(it) })
    }
}

class GroupMembersViewModel(groupId: Int?, viewLifecycleOwner: LifecycleOwner) : ViewModel() {

    private val _members: MutableLiveData<List<User>> = MutableLiveData()
    val members: LiveData<List<User>> = _members

    init {
        GlobalScope.launch {
            if (groupId != null) {
                //TODO load from DB here
            }
        }
    }
}

class GroupMembersViewModelFactory(application: Application, private val groupId: Int?, private val viewLifecycleOwner: LifecycleOwner) :
    ViewModelProvider.AndroidViewModelFactory(application) {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GroupMembersViewModel(groupId, viewLifecycleOwner) as T
    }

}


class GroupMemberAdapter : BasicAdapter<User, GroupMemberAdapter.GroupMemberViewHolder>() {

    private lateinit var listView: CardView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupMemberViewHolder {
        listView = LayoutInflater.from(parent.context).inflate(R.layout.item_group_big, parent, false) as CardView
        return GroupMemberViewHolder(listView)
    }

    override fun onBindViewHolder(holder: GroupMemberViewHolder, position: Int) {
        holder.bind(data[position])
    }

    class GroupMemberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(user: User) {
            val name = itemView.findViewById<TextView>(R.id.item_name)
            name.text = user.username
        }
    }
}

