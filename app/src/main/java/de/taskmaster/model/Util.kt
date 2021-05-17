package de.taskmaster.model

import android.graphics.Bitmap
import android.graphics.Matrix
import android.view.View


fun Bitmap.rotate(degrees: Float): Bitmap {
    val matrix = Matrix().apply { postRotate(degrees) }
    return Bitmap.createBitmap(this, 0, 0, width, height, matrix, true)
}

fun View.toggleVisibility() {
    visibility = when (visibility) {
        View.GONE -> {
            View.VISIBLE
        }
        else -> {
            View.GONE
        }
    }
}