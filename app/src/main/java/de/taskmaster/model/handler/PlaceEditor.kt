package de.taskmaster.model.handler

import android.view.View
import de.taskmaster.model.data.Address
import de.taskmaster.model.data.Group

interface PlaceEditor : ViewHandler, PlaceCreator, PlaceRemover

interface PlaceRemover {
    fun remove(address: Address)
}

interface PlaceCreator {
    fun add(address: Address)
}

interface GroupSelector {
    fun selectGroup(group: Group)
}

interface ViewHandler {
    fun getView(id: Int): View
}