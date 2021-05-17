package de.taskmaster.activity.app.ui.profile.settings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import de.taskmaster.R
import de.taskmaster.activity.util.BasicAdapter
import de.taskmaster.model.data.impl.Address
import de.taskmaster.model.handler.PlaceRemover

class PlaceAdapter(private val handler: PlaceRemover) : BasicAdapter<Address, PlaceAdapter.PlaceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        return PlaceViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_place, parent, false) as CardView)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        holder.bind(data[position], handler)
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
