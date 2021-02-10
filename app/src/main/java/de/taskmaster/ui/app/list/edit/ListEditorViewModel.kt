package de.taskmaster.ui.app.list.edit

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import de.taskmaster.model.data.Group
import de.taskmaster.model.data.Repeat
import de.taskmaster.model.data.TaskList
import de.taskmaster.model.data.User

class ListEditorViewModel : BaseObservable() {

    private val _user = MutableLiveData(User())
    val user: LiveData<User> = _user

    @get:Bindable
    val taskList: TaskList = TaskList()


    fun setDeadline(daysAhead: Int) {
        /* val taskList = taskList.value ?: error("tasklist null")
         taskList.deadline = LocalDate.now().plusDays(daysAhead.toLong())
         _taskList.postValue(taskList)
    */
    }

    fun setRepeat(repeat: Repeat) {
        /*   val taskList = taskList.value ?: error("tasklist null")
           taskList.repeat = repeat
           _taskList.postValue(taskList)
      */
    }

    fun setGroup(group: Group) {
        /*   val copy = taskList.value ?: error("group null")
           copy.group = group
           _taskList.postValue(copy)
       */
    }

}