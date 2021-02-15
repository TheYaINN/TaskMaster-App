package de.taskmaster.activity.app.ui.group.editor.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import de.taskmaster.R
import de.taskmaster.activity.app.ui.group.BigGroupAdapter
import de.taskmaster.activity.app.ui.list.ListOverviewAdapter
import de.taskmaster.activity.util.BasicAdapter
import de.taskmaster.model.data.Group

class GroupListsFragment : Fragment(R.layout.fragment_lists_members) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = GroupListAdapter()
        recyclerView.adapter = adapter
        adapter.setData(listOf(Group().apply { title = "Test 1" }, Group().apply { title = "Test 2" }))
    }
}

class GroupListAdapter : BasicAdapter<Group, GroupListAdapter.GroupListViewHolder>() {

    private lateinit var listView: CardView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupListViewHolder {
        listView = LayoutInflater.from(parent.context).inflate(R.layout.item_group_big, parent, false) as CardView
        return GroupListViewHolder(listView)
    }

    override fun onBindViewHolder(holder: GroupListViewHolder, position: Int) {
        holder.bind(data[position])
    }

    class GroupListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(group: Group) {
            //TODO
            val name = itemView.findViewById<TextView>(R.id.item_name)
            val description = itemView.findViewById<TextView>(R.id.item_description)
            name.text = group.title
        }

    }
}

