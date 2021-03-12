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
            var user: User? = null
            /* TODO:
            val latch = CountDownLatch(1)
             GlobalScope.launch {
                 user = LocalDataBaseConnector.instance.userDAO.getByUserName(viewModel.userName)
                 latch.countDown()
             }
             latch.await()
             if (user != null && SecurityHelper.instance.validatePassword(viewModel.password, user!!.password, user!!.salt, user!!.iterations)) {*/
            if (true) {
                if (viewModel.rememberMe) {
                    saveLoginInformation(context, viewModel)
                }
                return true
            }
            return false
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

