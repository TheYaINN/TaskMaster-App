package de.taskmaster.model.handler

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import de.taskmaster.R
import de.taskmaster.auth.LocalAuthHelper
import de.taskmaster.ui.login.LoginViewModel
import de.taskmaster.ui.util.ActivityHelper

class LoginHandler(private val context: Context, private val activity: Activity) {

    fun tryLogin(view: View, model: LoginViewModel) {
        if (LocalAuthHelper.login(model.user, model.rememberMe, context)) {
            ActivityHelper.startAppActivity(activity)
        } else {
            (view.parent as ConstraintLayout).findViewById<EditText>(R.id.password).text = null
            Toast.makeText(context, context.getString(R.string.login_failed), Toast.LENGTH_LONG).show()
        }
    }
}