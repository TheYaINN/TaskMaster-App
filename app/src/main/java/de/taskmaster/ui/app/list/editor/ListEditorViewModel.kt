package de.taskmaster.ui.app.list.editor

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import de.taskmaster.BR
import de.taskmaster.model.data.Repeat
import de.taskmaster.model.data.TaskList
import java.time.LocalDate

class ListEditorViewModel : BaseObservable() {

    @get:Bindable
    var taskList: TaskList = TaskList()
        set(value) {
            field = value
            notifyPropertyChanged(BR.user)
        }

    fun setDeadline(daysAhead: Int) {
        taskList.deadline = LocalDate.now().plusDays(daysAhead.toLong())
    }

    fun setRepeat(repeat: Repeat) {
        taskList.repeat = repeat
    }

}