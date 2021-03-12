package de.taskmaster.model.handler

import android.view.View
import de.taskmaster.activity.login.RegisterViewModel
import de.taskmaster.auth.SecurityHelper
import de.taskmaster.db.LocalDataBaseConnector
import de.taskmaster.model.data.impl.User

class RegistrationHandler {

    fun register(view: View, model: RegisterViewModel) {
        if (!model.isValid()) {
            return
        }
        val hashedPassword = SecurityHelper.instance.generateHashedPassword(model.password)
        val user = User(
            0,
            null,
            model.userName,
            hashedPassword[2].toString(),
            hashedPassword[1].toString(),
            hashedPassword[0].toInt(),
            model.firstName,
            model.lastName,
            model.email
        )
        LocalDataBaseConnector.instance.insert(user)
    }
}