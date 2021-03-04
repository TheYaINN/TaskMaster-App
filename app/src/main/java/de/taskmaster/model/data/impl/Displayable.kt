package de.taskmaster.model.data.impl

import android.graphics.Bitmap

interface Displayable {

    fun getImage(): Bitmap?

    fun rotate()

}