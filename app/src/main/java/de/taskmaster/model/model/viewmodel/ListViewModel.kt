package de.taskmaster.model.model.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.taskmaster.model.data.TaskList

class ListViewModel : ViewModel() {

    private val _lists = MutableLiveData<List<TaskList>>() //TODO: load data here from DB
    val lists: LiveData<List<TaskList>> = _lists

}