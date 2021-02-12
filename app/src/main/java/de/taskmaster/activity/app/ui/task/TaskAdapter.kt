package de.taskmaster.activity.app.ui.task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import de.taskmaster.R
import de.taskmaster.activity.util.BasicAdapter
import de.taskmaster.model.data.Status
import de.taskmaster.model.data.Task


class TaskAdapter(private val fragment: TaskOverview) : BasicAdapter<Task, TaskAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false) as CardView)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(data[position], fragment)
    }

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(task: Task, fragment: TaskOverview) {
            val title = itemView.findViewById<TextView>(R.id.item_title)
            val description = itemView.findViewById<TextView>(R.id.description)
            val status = itemView.findViewById<ImageView>(R.id.item_status)

            title.text = task.title
            description.text = task.description
            status.setImageDrawable(AppCompatResources.getDrawable(fragment.requireContext(), task.status.resID))
            itemView.setOnClickListener {
                task.status = when (task.status) {
                    Status.OPEN -> Status.FINISHED
                    else -> Status.OPEN
                }
            }
        }
    }
}