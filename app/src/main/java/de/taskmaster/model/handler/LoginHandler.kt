package de.taskmaster.model.handler

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import de.taskmaster.R
import de.taskmaster.activity.app.AppActivity
import de.taskmaster.activity.util.ActivityHelper
import de.taskmaster.auth.LocalAuthHelper
import de.taskmaster.model.data.User

class LoginHandler(private val context: Context, private val activity: Activity) {

    fun tryLogin(view: View, model: User) {
        if (LocalAuthHelper.login(model, context)) {
            ActivityHelper.startActivity(activity, AppActivity::class.java)
        } else {
            (view.parent as ConstraintLayout).findViewById<EditText>(R.id.password).text = null
            Toast.makeText(context, context.getString(R.string.login_failed), Toast.LENGTH_LONG).show()
        }
    }
}