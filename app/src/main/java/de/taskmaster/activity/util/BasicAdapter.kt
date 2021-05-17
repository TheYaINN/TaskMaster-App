package de.taskmaster.activity.util

import androidx.recyclerview.widget.RecyclerView

/**
 * @param T is the datatype that is displayed in the Recyclerview.
 *  The DataType is always wrapped in a list, since this RecyclerViews are used to display lists of data.
 *
 * @param V is the class of the ViewHolder, that is implemented.
 */
abstract class BasicAdapter<T, V : RecyclerView.ViewHolder> : RecyclerView.Adapter<V>() {

    /**
     * The Data list is always initialized as an empty array to avoid NPEs.
     */
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