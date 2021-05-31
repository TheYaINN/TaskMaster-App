package de.taskmaster.model.handler

import android.view.View
import android.widget.Toast
import de.taskmaster.activity.login.RegisterViewModel
import de.taskmaster.auth.SecurityHelper
import de.taskmaster.db.LocalDataBaseConnector
import de.taskmaster.model.data.impl.User
import kotlinx.coroutines.runBlocking

class RegistrationHandler {

    fun register(view: View, model: RegisterViewModel) {
        if (model.isValid() && !isExisting(model.userName)) {
            val hashedPassword = SecurityHelper.generateHashedPassword(model.password).split(":")
            val user = User(
                0,
                null,
                model.userName,
                hashedPassword[2],
                hashedPassword[1],
                hashedPassword[0].toInt(),
                model.firstName,
                model.lastName,
                model.email
            )
            runBlocking {
                LocalDataBaseConnector.instance.userDAO.insert(user)
            }
            Toast.makeText(view.context, "Registration sucessful", Toast.LENGTH_LONG).show()
            model.clear()
            return
        }
        Toast.makeText(view.context, "Registration unsucessful", Toast.LENGTH_LONG).show()
    }

    private fun isExisting(username: String): Boolean {
        return LocalDataBaseConnector.instance.userDAO.getByUserName(username) != null
    }
}