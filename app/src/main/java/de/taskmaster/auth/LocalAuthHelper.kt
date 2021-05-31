package de.taskmaster.auth

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import de.taskmaster.activity.login.LoginViewModel
import de.taskmaster.db.LocalDataBaseConnector
import kotlinx.coroutines.runBlocking

class LocalAuthHelper {

    companion object {
        private const val preferencesKey = "de.taskmaster"
        private const val usernameKey = "username"
        private const val useridKey = "userid"
        private const val passwordKey = "password"

        fun getUserId(context: Context): Int {
            return context.getSharedPreferences(preferencesKey, MODE_PRIVATE).getInt(useridKey, -1)
        }

        fun login(viewModel: LoginViewModel, context: Context): Boolean {
            var success = false
            runBlocking {
                val user = LocalDataBaseConnector.instance.userDAO.getByUserName(viewModel.userName)
                if (user != null) {
                    if (SecurityHelper.validatePassword(viewModel.password, user.password, user.salt, user.iterations)) {
                        val sp = context.getSharedPreferences(preferencesKey, MODE_PRIVATE)
                        val editor: SharedPreferences.Editor = sp.edit()
                        editor.putInt(useridKey, user.userId)
                        editor.apply()
                        if (viewModel.rememberMe) {
                            saveLoginInformation(context, viewModel)
                        }
                        success = true
                    }
                } else {
                    Toast.makeText(context,
                        "Could not find User, please re-enter Username and password",
                        Toast.LENGTH_LONG).show()
                }
            }
            viewModel.clear()
            return success
        }


        private fun saveLoginInformation(context: Context, viewModel: LoginViewModel) {
            val sp = context.getSharedPreferences(preferencesKey, MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sp.edit()
            editor.putString(usernameKey, viewModel.userName)
            editor.putString(passwordKey, viewModel.password)
            editor.apply()
        }

        private fun getLoginInformation(context: Context): LoginViewModel {
            val loginInformation = context.getSharedPreferences(preferencesKey, MODE_PRIVATE)
            val viewModel = LoginViewModel()
            viewModel.userName = loginInformation.getString(usernameKey, null) ?: ""
            viewModel.password = loginInformation.getString(passwordKey, null) ?: ""
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

