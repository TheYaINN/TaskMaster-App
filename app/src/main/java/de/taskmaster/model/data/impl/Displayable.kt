package de.taskmaster.model.data.impl

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import de.taskmaster.model.rotate

abstract class Displayable : ObservableViewModel() {

    private var _image: MutableLiveData<Bitmap> = MutableLiveData()
    var image: LiveData<Bitmap> = _image

    fun rotate() {
        val tempImage = image.value
        tempImage?.rotate(90f)
        _image.postValue(tempImage)
    }

    fun updateImage(img: Bitmap) {
        _image.postValue(img)
    }

}