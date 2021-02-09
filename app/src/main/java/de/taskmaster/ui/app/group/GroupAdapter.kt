package de.taskmaster.ui.app.group

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
import de.taskmaster.model.data.Group

class GroupAdapter(private val fragment: Fragment) : RecyclerView.Adapter<GroupAdapter.GroupViewHolder>() {

    private lateinit var listView: CardView

    private var data: MutableList<Group> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        listView = inflater.inflate(R.layout.item_group, parent, false) as CardView
        return GroupViewHolder(listView)
    }

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
        holder.bind(data[position])
        listView.setOnClickListener {
            //TODO: somehow remember which id i pressed on to edit it here
            fragment.findNavController().navigate(R.id.action_navigation_group_to_groupEditorFragment)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(newData: List<Group>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    class GroupViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(group: Group) {
            val icon = itemView.findViewById<ImageView>(R.id.item_icon)
            val name = itemView.findViewById<TextView>(R.id.item_name)
            val description = itemView.findViewById<TextView>(R.id.item_description)

            //TODO: icon.setImageDrawable()
            name.text = group.title
            description.text = group.description
        }
    }
}


