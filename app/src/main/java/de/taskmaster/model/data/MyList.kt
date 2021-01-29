package de.taskmaster.model.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate

class MyList : ViewModel() {

    private val _title = MutableLiveData("")
    val title: LiveData<String> = _title

    var visible: Boolean = true

    private val _deadline = MutableLiveData(LocalDate.now())
    val deadline: LiveData<LocalDate> = _deadline

    var place: String? = null
    var repeat: String? = null
    fun update() {
        println("SOUTING DU BITCH")
    }

}