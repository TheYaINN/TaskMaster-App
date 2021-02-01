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
            //FIXME
            //val response = ServerConnector.INSTANCE.postRequest("login", userData.first, userData.second)
            if (!user.username.isBlank()) {
                if (rememberUser) {
                    saveLoginInformation(context, user)
                }
                return true
            }
            return false
        }

        //TODO: at this point only the hashed password should be saved
        fun saveLoginInformation(context: Context, user: User) {
            val sp = context.getSharedPreferences(preferencesKey, MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sp.edit()
            editor.putString("username", user.username)
            editor.putString("password", user.password)
            editor.apply()
        }

        fun getLoginInformation(context: Context): User {
            val loginInformation = context.getSharedPreferences(preferencesKey, MODE_PRIVATE)
            val username = loginInformation.getString(usernameKey, "")
            val password = loginInformation.getString(passwordKey, "")
            requireNotNull(username)
            requireNotNull(password)
            return User(username, password)
        }

        fun onStartUp(context: Context): Boolean {
            return false //TODO: remove this line only for testing purpose rn
            return login(getLoginInformation(context), false, context)
        }

        fun removeLoginInformation(context: Context) {
            context.getSharedPreferences(preferencesKey, MODE_PRIVATE)
                .edit().clear().apply()
        }
    }


}

