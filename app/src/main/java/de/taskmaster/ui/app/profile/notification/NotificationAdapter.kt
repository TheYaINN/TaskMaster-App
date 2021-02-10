package de.taskmaster.ui.app.profile.notification

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.RecyclerView
import de.taskmaster.R
import de.taskmaster.model.data.Address


class NotificationAdapter : RecyclerView.Adapter<NotificationAdapter.ListViewHolder>() {

    private var data: MutableList<Address> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_notification, parent, false))
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(newData: List<Address>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(address: Address) {
            val title = itemView.findViewById<TextView>(R.id.item_title)
            title.text = address.toString()

            val notify = itemView.findViewById<SwitchCompat>(R.id.item_switch)
            notify.isChecked = address.notifiable

            notify.setOnCheckedChangeListener { _, isChecked ->
                address.notifiable = isChecked
            }
        }
    }

}