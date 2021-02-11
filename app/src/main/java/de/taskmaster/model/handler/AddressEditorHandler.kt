package de.taskmaster.model.handler

import android.content.Context
import android.view.View
import android.widget.TextView
import android.widget.Toast
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


        val name = view.getView(R.id.item_title) as TextView
        val street = view.getView(R.id.item_place_street) as TextView
        val number = view.getView(R.id.item_place_number) as TextView
        val zipCode = view.getView(R.id.item_place_zip) as TextView
        val city = view.getView(R.id.item_place_city) as TextView

        //TODO: refactor, may not be done here
        address.name = name.text.toString()
        address.street = street.text.toString()
        address.number = number.text.toString().toInt()
        address.zipCode = zipCode.text.toString().toInt()
        address.city = city.text.toString()

        if (!address.isValid()) {
            Toast.makeText(context, "Please enter valid Address", Toast.LENGTH_LONG).show()
        } else {

            toggleAdd()

            name.text = null
            street.text = null
            number.text = null
            zipCode.text = null
            city.text = null

            view.add(address)
        }
    }


}