package de.taskmaster.ui.app.profile

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import de.taskmaster.BR
import de.taskmaster.model.data.User

class UserViewModel : BaseObservable() {

    @get:Bindable
    var user: User = User()
        set(value) {
            field = value
            notifyPropertyChanged(BR.user)
        }

}