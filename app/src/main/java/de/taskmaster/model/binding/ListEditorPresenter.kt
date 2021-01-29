package de.taskmaster.model.binding

import android.content.Context
import android.view.View

class ListEditorPresenter(val view: ListEditorContract.View, val context: Context) {

    fun toggleView(view: View) {
        this.view.toggle(view.id)
    }

}