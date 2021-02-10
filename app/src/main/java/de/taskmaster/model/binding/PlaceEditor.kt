package de.taskmaster.model.binding

import android.view.View
import de.taskmaster.model.data.Address

interface PlaceEditor : PlaceCreator, PlaceRemover {

    fun getView(id: Int): View

}

interface PlaceRemover {
    fun remove(address: Address)
}

interface PlaceCreator {
    fun add(address: Address)
}
