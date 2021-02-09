package de.taskmaster.model.binding

import android.content.Context
import android.view.View
import android.widget.TextView
import de.taskmaster.R
import de.taskmaster.model.data.Address

class AddressEditorHandler(val view: PlaceEditor, context: Context) : ToggleEditableComponentHandler(context, "Show", "Hide") {

    fun toggleAdd() {
        val editor = view.getView(R.id.item_editor)
        editor.visibility = when (editor.visibility) {
            View.GONE -> View.VISIBLE
            else -> View.GONE
        }
    }

    fun save() {
        val address = Address()

        //TODO: check if fields are valid

        //TODO: refactor I guess
        address.street = (view.getView(R.id.item_place_street) as TextView).text.toString()
        //address.number = (view.getView(R.id.item_place_number) as TextView).text.toString().toInt()
        //address.zipCode = (view.getView(R.id.item_place_zip) as TextView).text.toString().toInt()
        address.city = (view.getView(R.id.item_place_city) as TextView).text.toString()

        view.add(address)
        toggleAdd()
    }


}