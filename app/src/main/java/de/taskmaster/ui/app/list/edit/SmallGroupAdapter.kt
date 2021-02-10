package de.taskmaster.ui.app.list.edit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import de.taskmaster.R
import de.taskmaster.model.data.Group
import de.taskmaster.model.handler.GroupSelector

class SmallGroupAdapter(private val handler: GroupSelector) : RecyclerView.Adapter<SmallGroupAdapter.SmallGroupViewHolder>() {

    private lateinit var listView: CardView

    private var data: MutableList<Group> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SmallGroupViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        listView = inflater.inflate(R.layout.item_group_small, parent, false) as CardView
        return SmallGroupViewHolder(listView)
    }

    override fun onBindViewHolder(holder: SmallGroupViewHolder, position: Int) {
        holder.bind(data[position])
        listView.setOnClickListener {
            handler.selectGroup(data[position])
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

    class SmallGroupViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(group: Group) {
            val title = itemView.findViewById<TextView>(R.id.item_title)
            title.text = group.title
        }
    }
}
