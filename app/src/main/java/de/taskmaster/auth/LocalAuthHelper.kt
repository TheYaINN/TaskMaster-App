package de.taskmaster.auth

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import de.taskmaster.activity.login.LoginViewModel
import de.taskmaster.model.data.impl.User

class LocalAuthHelper {

    companion object {
        private const val preferencesKey = "de.taskmaster"
        private const val usernameKey = "username"
        private const val passwordKey = "password"

        fun login(viewModel: LoginViewModel, context: Context): Boolean {
            //TODO before sending anything to the server, the password should be hashed
            //val response = ServerConnector.INSTANCE.postRequest("login", userData.first, userData.second)
            if (viewModel.rememberMe) {
                saveLoginInformation(context, viewModel.user)
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

        private fun getLoginInformation(context: Context): LoginViewModel {
            val loginInformation = context.getSharedPreferences(preferencesKey, MODE_PRIVATE)
            val viewModel = LoginViewModel()
            viewModel.user.username = loginInformation.getString(usernameKey, "") ?: ""
            viewModel.user.password = loginInformation.getString(passwordKey, "") ?: ""
            return viewModel
        }

        fun onStartUp(context: Context): Boolean {
            return login(getLoginInformation(context), context)
        }

        fun removeLoginInformation(context: Context) {
            context.getSharedPreferences(preferencesKey, MODE_PRIVATE)
                .edit().clear().apply()
        }
    }


}

