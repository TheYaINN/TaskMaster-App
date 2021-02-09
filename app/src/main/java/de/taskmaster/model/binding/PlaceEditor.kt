package de.taskmaster.model.binding

import android.view.View
import de.taskmaster.model.data.Address

interface PlaceEditor {

    fun add(address: Address)

    fun getView(id: Int): View

}