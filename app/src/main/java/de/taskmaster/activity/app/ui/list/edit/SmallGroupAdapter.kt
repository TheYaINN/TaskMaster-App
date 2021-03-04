package de.taskmaster.activity.app.ui.list.edit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import de.taskmaster.R
import de.taskmaster.activity.util.BasicAdapter
import de.taskmaster.model.data.impl.Group
import de.taskmaster.model.handler.GroupSelector

class SmallGroupAdapter(val fragment: Fragment) : BasicAdapter<Group, SmallGroupAdapter.SmallGroupViewHolder>() {

    private lateinit var listView: CardView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SmallGroupViewHolder {
        listView = LayoutInflater.from(parent.context).inflate(R.layout.item_group_small, parent, false) as CardView
        return SmallGroupViewHolder(listView)
    }

    override fun onBindViewHolder(holder: SmallGroupViewHolder, position: Int) {
        holder.bind(data[position])
        listView.setOnClickListener {
            listView.background = AppCompatResources.getDrawable(fragment.requireContext(), R.drawable.highlight)
            (fragment as GroupSelector).selectGroup(data[position])
        }
    }

    inner class SmallGroupViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(group: Group) {
            val title = itemView.findViewById<TextView>(R.id.item_title)
            title.text = group.title
        }
    }
}
