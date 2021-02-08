package de.taskmaster.ui.app.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import de.taskmaster.R
import de.taskmaster.model.data.TaskList

class ListAdapter : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    private var data: MutableList<TaskList> = mutableListOf()

    //TODO: nothing has been done in here, just added so the code works for now
    private lateinit var listView: CardView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        listView = inflater.inflate(R.layout.list_item, parent, false) as CardView
        return ListViewHolder(listView)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return 0
    }

    fun setData(newData: List<TaskList>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind() {}

    }

}
