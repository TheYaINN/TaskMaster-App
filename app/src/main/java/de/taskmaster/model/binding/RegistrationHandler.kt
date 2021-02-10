package de.taskmaster.model.binding

import android.view.View
import de.taskmaster.model.model.UserViewModel

class RegistrationHandler {

    fun register(view: View, model: UserViewModel) {
        println("REGISTERING: $model On VIEW: ${view.id}")
    }
}