package de.taskmaster.ui.app.profile.settings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import de.taskmaster.R
import de.taskmaster.model.data.Address
import de.taskmaster.model.handler.PlaceRemover

class PlaceAdapter(private val handler: PlaceRemover) : RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>() {

    private var data: MutableList<Address> = mutableListOf()

    private lateinit var listView: CardView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        listView = inflater.inflate(R.layout.item_place, parent, false) as CardView
        return PlaceViewHolder(listView)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        holder.bind(data[position], handler)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(newData: List<Address>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    class PlaceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(address: Address, handler: PlaceRemover) {
            itemView.findViewById<TextView>(R.id.item_place_title).text = address.toString()
            itemView.findViewById<ImageView>(R.id.item_place_delete).setOnClickListener {
                handler.remove(address)
            }
        }
    }

}
