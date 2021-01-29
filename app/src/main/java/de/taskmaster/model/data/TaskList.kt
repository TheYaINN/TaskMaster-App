package de.taskmaster.model.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate

class TaskList : ViewModel() {

    private val _title = MutableLiveData("Hier ist ein Titel")
    val title: LiveData<String> = _title

    private val _deadline = MutableLiveData(LocalDate.now())
    val deadline: LiveData<LocalDate> = _deadline

    private val _description = MutableLiveData("Eine Beschreibung")
    val description: LiveData<String> = _description

    private val _tags = MutableLiveData("Tag1, Tag2, Tag3")
    val tags: LiveData<String> = _tags

    private val _repeat = MutableLiveData(Repeat.DAILY)
    val repeat: LiveData<Repeat> = _repeat

    private val _group = MutableLiveData<List<Group>>(arrayListOf())
    val group: LiveData<List<Group>> = _group


    fun setRepeat(value: Repeat) {
        _repeat.postValue(value)
    }

    fun setDeadline(days: Int) {
        _deadline.postValue(LocalDate.now().plusDays(days.toLong()))
    }

}