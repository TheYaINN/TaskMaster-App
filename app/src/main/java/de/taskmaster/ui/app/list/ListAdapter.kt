package de.taskmaster.ui.app.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import de.taskmaster.R
import de.taskmaster.model.data.TaskList

class ListAdapter(private val context: Context) : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    private var data: MutableList<TaskList> = mutableListOf()

    private lateinit var listView: CardView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        listView = inflater.inflate(R.layout.list_item, parent, false) as CardView
        return ListViewHolder(listView)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(data[position], context)
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

        fun bind(taskList: TaskList, context: Context) {
            val title = itemView.findViewById<TextView>(R.id.item_title)
            val subTitle = itemView.findViewById<TextView>(R.id.item_subtitle)
            val status = itemView.findViewById<ImageView>(R.id.item_status)

            title.text = taskList.title
            subTitle.text = taskList.description
            status.setImageDrawable(AppCompatResources.getDrawable(context, taskList.status.resID))
        }

    }

}
