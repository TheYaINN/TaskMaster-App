package de.taskmaster.activity.util

import androidx.recyclerview.widget.RecyclerView

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