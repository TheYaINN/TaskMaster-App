package de.taskmaster.ui.app.list

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
import de.taskmaster.model.data.Status
import de.taskmaster.model.data.TaskList

class ListOverviewAdapter(private val fragment: ListOverviewFragment) : RecyclerView.Adapter<ListOverviewAdapter.ListViewHolder>() {

    private var data: MutableList<TaskList> = mutableListOf()

    private lateinit var listView: CardView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        listView = inflater.inflate(R.layout.item_list, parent, false) as CardView
        return ListViewHolder(listView)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(data[position], fragment)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(newData: List<TaskList>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(taskList: TaskList, fragment: ListOverviewFragment) {
            val title = itemView.findViewById<TextView>(R.id.item_title)
            val subTitle = itemView.findViewById<TextView>(R.id.item_subtitle)
            val status = itemView.findViewById<ImageView>(R.id.item_status)
            title.text = taskList.title
            subTitle.text = taskList.description
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
