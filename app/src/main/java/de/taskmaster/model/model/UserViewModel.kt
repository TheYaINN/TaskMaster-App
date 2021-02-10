package de.taskmaster.model.model

import androidx.lifecycle.ViewModel
import de.taskmaster.model.data.ObservableUser
import de.taskmaster.model.data.User

class UserViewModel : ViewModel() {

    val user = ObservableUser(User())

}
