package de.taskmaster.activity.app.ui.list.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import de.taskmaster.R
import de.taskmaster.model.data.Task


class TaskAdapter : RecyclerView.Adapter<TaskAdapter.ListViewHolder>() {

    private var data: MutableList<Task> = mutableListOf()

    private lateinit var listView: CardView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        listView = inflater.inflate(R.layout.item_list, parent, false) as CardView
        return ListViewHolder(listView)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(newData: List<Task>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(taskList: Task) {

        }

    }

}
