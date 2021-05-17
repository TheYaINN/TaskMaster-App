package de.taskmaster.model

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.core.view.drawToBitmap
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter

object Converter {

    @BindingAdapter("android:src")
    @JvmStatic
    fun setImageDrawable(view: ImageView, source: Bitmap?) {
        view.setImageBitmap(source)
    }

    @InverseBindingAdapter(attribute = "android:src", event = "android:srcAttrChanged")
    @JvmStatic
    fun getImageDrawable(view: ImageView): Bitmap {
        return view.drawToBitmap()
    }

}