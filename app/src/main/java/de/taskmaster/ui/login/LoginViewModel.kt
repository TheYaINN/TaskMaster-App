package de.taskmaster.ui.login

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import de.taskmaster.BR
import de.taskmaster.model.data.User

class LoginViewModel : BaseObservable() {

    @get:Bindable
    var user: User = User()
        set(value) {
            field = value
            notifyPropertyChanged(BR.user)
        }

    @get:Bindable
    var rememberMe: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.rememberMe)
        }

}