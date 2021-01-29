package de.taskmaster.auth

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE

class LocalAuthHelper {

    companion object {
        private const val preferencesKey = "Taskmaster-Login"
        private const val usernameKey = "username"
        private const val passwordKey = "password"

        fun login(userData: Pair<String, String>, rememberUser: Boolean, context: Context): Boolean {
            //FIXME
            //val response = ServerConnector.INSTANCE.postRequest("login", userData.first, userData.second)
            if (true) {
                if (rememberUser) {
                    saveLoginInformation(context, userData.first, userData.second)
                }
                return true
            }
            return false
        }


        fun saveLoginInformation(context: Context, username: String, password: String) {
            val sp = context.getSharedPreferences(preferencesKey, MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sp.edit()
            editor.putString("username", username)
            editor.putString("password", password)
            editor.apply()
        }

        fun getLoginInformation(context: Context): Pair<String, String> {
            val loginInformation = context.getSharedPreferences(preferencesKey, MODE_PRIVATE)
            val username = loginInformation.getString(usernameKey, "")
            val password = loginInformation.getString(passwordKey, "")
            requireNotNull(username)
            requireNotNull(password)
            return Pair(username, password)
        }

        fun removeLoginInformation(context: Context) {
            context.getSharedPreferences(preferencesKey, MODE_PRIVATE)
                .edit().clear().apply()
        }
    }


}

