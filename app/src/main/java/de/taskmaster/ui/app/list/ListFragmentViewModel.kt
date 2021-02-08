package de.taskmaster.ui.app.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.taskmaster.model.data.TaskList

class ListFragmentViewModel : ViewModel() {

    private val _lists = MutableLiveData<List<TaskList>>(arrayListOf(TaskList(), TaskList(), TaskList(), TaskList(), TaskList(), TaskList()))
    val lists: LiveData<List<TaskList>> = _lists

}