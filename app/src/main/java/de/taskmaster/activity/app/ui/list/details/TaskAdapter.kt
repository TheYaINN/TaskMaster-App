package de.taskmaster.activity.app.ui.list.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import de.taskmaster.R
import de.taskmaster.model.data.Task


class TaskAdapter(private val fragment: ListFragment) : BasicAdapter<Task, TaskAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false) as CardView)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(data[position], fragment)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(task: Task, fragment: ListFragment) {
            val title = itemView.findViewById<TextView>(R.id.item_title)
            val description = itemView.findViewById<TextView>(R.id.description)
            val status = itemView.findViewById<ImageView>(R.id.item_status)

            title.text = task.title
            description.text = task.description
            status.setImageDrawable(AppCompatResources.getDrawable(fragment.requireContext(), task.status.resID))
            itemView.setOnClickListener {
                //TODO: add navigation
            }
        }
    }

}


abstract class BasicAdapter<T, V : RecyclerView.ViewHolder> : RecyclerView.Adapter<V>() {

    val data: MutableList<T> = mutableListOf()

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(newData: List<T>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

}