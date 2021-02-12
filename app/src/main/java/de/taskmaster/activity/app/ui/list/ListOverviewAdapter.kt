package de.taskmaster.activity.app.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import de.taskmaster.R
import de.taskmaster.activity.util.BasicAdapter
import de.taskmaster.model.data.Status
import de.taskmaster.model.data.TaskList

class ListOverviewAdapter(private val fragment: ListOverviewFragment) : BasicAdapter<TaskList, ListOverviewAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false) as CardView)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(data[position], fragment)
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(taskList: TaskList, fragment: ListOverviewFragment) {
            val title = itemView.findViewById<TextView>(R.id.item_title)
            val description = itemView.findViewById<TextView>(R.id.description)
            val status = itemView.findViewById<ImageView>(R.id.item_status)

            title.text = taskList.title
            description.text = taskList.description
            status.setImageDrawable(AppCompatResources.getDrawable(fragment.requireContext(), getIcon(taskList)))

            addListeners(taskList, fragment)
        }

        private fun getIcon(taskList: TaskList): Int {
            return when (taskList.tasks?.any { it.status == Status.OPEN }) {
                true -> R.drawable.clear
                else -> R.drawable.check
            }
        }

        private fun addListeners(taskList: TaskList, fragment: ListOverviewFragment) {
            val actions = itemView.findViewById<ImageView>(R.id.item_actions)
            actions.setOnClickListener {
                val popupMenu = PopupMenu(fragment.requireContext(), actions)
                popupMenu.inflate(R.menu.item_actions)
                popupMenu.setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.item_edit -> fragment.findNavController().navigate(R.id.action_navigation_list_to_listEditorFragment)
                        R.id.item_delete -> println("DELETING: $taskList") //TODO: remove item from DB here
                    }
                    true
                }
                popupMenu.show()
            }
            itemView.setOnClickListener {
                fragment.findNavController().navigate(R.id.action_navigation_list_to_listDetailsFragment)
            }
        }

    }

}
