package de.taskmaster.model.data

import android.graphics.Bitmap

class Task : Test {

    var img: Bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.RGBA_F16)

    var title: String = ""

    var description = ""

    var status: Status = Status.OPEN

    var responsiblePerson: User? = null


    override fun getImage(): Bitmap {
        return img
    }

}
