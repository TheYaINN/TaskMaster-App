package de.taskmaster.model.handler

import android.view.View
import de.taskmaster.model.data.User

class RegistrationHandler {

    fun register(view: View, model: User) {
        println("REGISTERING: $model On VIEW: ${view.id}")
    }
}