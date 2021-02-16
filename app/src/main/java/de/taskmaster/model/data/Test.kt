package de.taskmaster.model.data

import android.graphics.Bitmap

interface Displayable {

    fun getImage(): Bitmap?

    fun rotate()

}