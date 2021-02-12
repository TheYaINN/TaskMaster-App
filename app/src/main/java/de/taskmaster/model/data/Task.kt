package de.taskmaster.model.data

import android.graphics.drawable.Drawable

class Task : Test {

    var img: Drawable? = null

    var title: String = ""

    var description = ""

    var status: Status = Status.OPEN

    var responsiblePerson: User? = null


    override fun getImage(): Drawable? {
        return img
    }

}
