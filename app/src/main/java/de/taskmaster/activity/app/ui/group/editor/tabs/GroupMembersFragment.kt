package de.taskmaster.activity.app.ui.group.editor.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import de.taskmaster.R
import de.taskmaster.activity.util.BasicAdapter
import de.taskmaster.model.data.impl.User
import kotlinx.coroutines.launch

class GroupMembersFragment : Fragment(R.layout.fragment_lists_members) {

    private lateinit var viewModel: GroupMembersViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = GroupMemberAdapter()
        viewModel = ViewModelProvider(this).get(GroupMembersViewModel::class.java)

        recyclerView.adapter = adapter

        viewModel.members.observe(viewLifecycleOwner, { adapter.setData(it) })
    }
}

class GroupMembersViewModel : ViewModel() {
    private val _members = MutableLiveData<List<User>>()
    val members: LiveData<List<User>> = _members

    init {
        viewModelScope.launch {
            //TODO: replace loading from db
            val arr = arrayListOf<User>()
            for (i in 0 until 10) {
                arr.add(User(0, null, "Test $i", "", "", 100, "Bengt", "Joachimsohn", "bengt@joachimsohn.de"))
            }
            _members.postValue(arr)
        }
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
            val description = itemView.findViewById<TextView>(R.id.item_description)
            name.text = user.username
        }

    }
}

