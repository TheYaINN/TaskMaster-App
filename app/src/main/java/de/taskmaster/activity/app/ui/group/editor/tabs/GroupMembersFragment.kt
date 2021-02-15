package de.taskmaster.activity.app.ui.group.editor.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import de.taskmaster.R
import de.taskmaster.activity.app.ui.list.ListOverviewAdapter
import de.taskmaster.activity.util.BasicAdapter
import de.taskmaster.model.data.Group
import de.taskmaster.model.data.User

class GroupMembersFragment : Fragment(R.layout.fragment_lists_members) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = GroupMemberAdapter()
        recyclerView.adapter = adapter
        adapter.setData(listOf(User(), User()))
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

