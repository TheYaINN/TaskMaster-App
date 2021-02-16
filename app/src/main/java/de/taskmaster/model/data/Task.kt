package de.taskmaster.model.data

import android.graphics.Bitmap
import de.taskmaster.model.rotate

class Task : Displayable {

    var img: Bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.RGBA_F16)

    var title: String = "Title"

    var description = ""

    var status: Status = Status.OPEN

    var responsiblePerson: User? = null

    override fun getImage(): Bitmap {
        return img
    }

    override fun rotate() {
        img.rotate(90f)
    }

}
