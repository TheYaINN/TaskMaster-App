package de.taskmaster.auth

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import de.taskmaster.model.data.User

class LocalAuthHelper {

    companion object {
        private const val preferencesKey = "Taskmaster-Login"
        private const val usernameKey = "username"
        private const val passwordKey = "password"

        fun login(user: User, rememberUser: Boolean, context: Context): Boolean {
            //TODO before sending anything to the server, the password should be hashed
            //val response = ServerConnector.INSTANCE.postRequest("login", userData.first, userData.second)
            if (user.username != null) {
                if (rememberUser) { //TODO: this is not changed in the model when changed in the UI
                    saveLoginInformation(context, user)
                }
                return true
            }
            return false
        }


        private fun saveLoginInformation(context: Context, user: User) {
            val sp = context.getSharedPreferences(preferencesKey, MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sp.edit()
            editor.putString(usernameKey, user.username)
            editor.putString(passwordKey, user.password)
            editor.apply()
        }

        private fun getLoginInformation(context: Context): User {
            val loginInformation = context.getSharedPreferences(preferencesKey, MODE_PRIVATE)
            val user = User()
            user.username = loginInformation.getString(usernameKey, null)
            user.password = loginInformation.getString(passwordKey, null)
            return user
        }

        fun onStartUp(context: Context): Boolean {
            return login(getLoginInformation(context), false, context)
        }

        fun removeLoginInformation(context: Context) {
            context.getSharedPreferences(preferencesKey, MODE_PRIVATE)
                .edit().clear().apply()
        }
    }


}

