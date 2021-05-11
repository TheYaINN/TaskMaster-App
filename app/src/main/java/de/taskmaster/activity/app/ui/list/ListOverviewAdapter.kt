package de.taskmaster.activity.app.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import de.taskmaster.R
import de.taskmaster.activity.util.BasicAdapter
import de.taskmaster.model.data.impl.Status
import de.taskmaster.model.data.impl.TodoListWithAssociations

class ListOverviewAdapter(private val fragment: ListOverviewFragment) : BasicAdapter<TodoListWithAssociations, ListOverviewAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false) as CardView)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(data[position], fragment)
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(taskList: TodoListWithAssociations, fragment: ListOverviewFragment) {
            val title = itemView.findViewById<TextView>(R.id.item_title)
            val description = itemView.findViewById<TextView>(R.id.item_description)
            val status = itemView.findViewById<ImageView>(R.id.item_status)

            title.text = taskList.list.title
            description.text = taskList.list.description
            status.setImageDrawable(AppCompatResources.getDrawable(fragment.requireContext(), getIcon(taskList)))
            addListeners(taskList, fragment)
        }

        private fun getIcon(taskList: TodoListWithAssociations): Int {
            return when (taskList.tasks.any { it.status == Status.OPEN }) {
                true -> R.drawable.clear
                else -> R.drawable.check
            }
        }

        private fun addListeners(taskList: TodoListWithAssociations, fragment: ListOverviewFragment) {
            val actions = itemView.findViewById<ImageView>(R.id.item_actions)
            val bundle = bundleOf("id" to taskList.list.listId)
            actions.setOnClickListener {
                val popupMenu = PopupMenu(fragment.requireContext(), actions)
                popupMenu.inflate(R.menu.item_actions)
                popupMenu.setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.item_edit -> fragment.findNavController().navigate(R.id.action_navigation_list_to_listEditorFragment, bundle)
                        R.id.item_delete -> fragment.delete(taskList.list)
                    }
                    true
                }
                popupMenu.show()
            }
            itemView.setOnClickListener {
                fragment.findNavController().navigate(R.id.action_navigation_list_to_taskoverview, bundle)
            }
        }
    }
}
