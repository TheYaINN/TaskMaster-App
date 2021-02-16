package de.taskmaster.model.data

import android.graphics.Bitmap
import de.taskmaster.model.rotate

class User : ObservableViewModel(), Displayable {

    var img: Bitmap? = null

    var username: String? = "TheYaINN"

    var password: String? = null

    var firstName: String? = "Bengt"

    var lastName: String? = "Joachimsohn"

    var email: String? = "bengt@joachimsohn.de"

    var rememberMe: Boolean = false

    var groups: List<Group> = arrayListOf(Group().apply { title = "Group 1" }, Group().apply { title = "Group 2" })

    var lists: List<TaskList> =
        arrayListOf(TaskList().apply {
            tasks = arrayListOf(Task(), Task())
            title = "Test titel"
        }, TaskList().apply { title = "Test titel" })

    val places: MutableList<Address> = arrayListOf(Address(), Address(), Address(), Address())

    override fun getImage(): Bitmap? {
        return img
    }

    override fun rotate() {
        img?.rotate(90f)
    }
}