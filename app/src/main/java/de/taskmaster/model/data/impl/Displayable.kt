package de.taskmaster.model.data.impl

import android.graphics.Bitmap
import de.taskmaster.model.rotate

abstract class Displayable : ObservableViewModel() {

    var image: Bitmap? = null

    fun rotate() {
        image?.rotate(90f)
    }

    fun updateImage(img: Bitmap) {
        image = img
    }

}