package de.taskmaster.auth

import de.taskmaster.db.ServerConnector

class RegisterHelper(val firstname: String, val lastname: String, val username: String, val mail: String) {

    fun register(): Boolean {
        if (!checkForExisting()) {
            return true
        }
        return false
    }

    //Returns false if there is no exisiting user
    private fun checkForExisting(): Boolean {
        val response =
            ServerConnector.INSTANCE.getRequest(
                "/login/register/checkForExisting",
                mapOf(Pair("username", username), Pair("mail", mail))
            )
        return response.toBoolean()
    }

}