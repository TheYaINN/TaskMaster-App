package de.taskmaster.activity.app.ui.task

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
import de.taskmaster.db.LocalDataBaseConnector
import de.taskmaster.model.data.impl.Status
import de.taskmaster.model.data.impl.Task
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class TaskAdapter(private val fragment: TaskOverview, private val listId: Int) : BasicAdapter<Task, TaskAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.items_task, parent, false) as CardView, listId)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(data[position], fragment)
    }

    class TaskViewHolder(itemView: View, private val listId: Int) : RecyclerView.ViewHolder(itemView) {

        fun bind(task: Task, fragment: TaskOverview) {
            val title = itemView.findViewById<TextView>(R.id.item_title)
            val description = itemView.findViewById<TextView>(R.id.item_description)
            val status = itemView.findViewById<ImageView>(R.id.item_status)

            title.text = task.title
            description.text = task.description
            status.setImageDrawable(AppCompatResources.getDrawable(fragment.requireContext(), task.status.resID))

            status.setOnClickListener {
                task.status = when (task.status) {
                    Status.OPEN -> Status.FINISHED
                    else -> Status.OPEN
                }
                status.setImageDrawable(AppCompatResources.getDrawable(fragment.requireContext(), task.status.resID))
            }

            val actions = itemView.findViewById<ImageView>(R.id.item_actions)
            val bundle = bundleOf("taskId" to task.taskId, "listId" to listId)
            actions.setOnClickListener {
                val popupMenu = PopupMenu(fragment.requireContext(), actions)
                popupMenu.inflate(R.menu.item_actions)
                popupMenu.setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.item_edit -> {
                            fragment.findNavController().navigate(R.id.action_taskOverview_to_taskEditorFragment, bundle)
                        }
                        R.id.item_delete -> {
                            GlobalScope.launch {
                                LocalDataBaseConnector.instance.taskDAO.delete(task)
                            }
                        }
                    }
                    true
                }
                popupMenu.show()
            }
        }
    }
}
