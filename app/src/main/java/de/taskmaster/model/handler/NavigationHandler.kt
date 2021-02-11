package de.taskmaster.model.handler

import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import de.taskmaster.R
import de.taskmaster.activity.app.AppActivity
import de.taskmaster.activity.login.LoginActivity
import de.taskmaster.activity.util.ActivityHelper
import de.taskmaster.auth.LocalAuthHelper
import de.taskmaster.model.data.User

class NavigationHandler(val fragment: Fragment) {

    fun toAccountSetting() {
        fragment.findNavController().navigate(R.id.action_navigation_profile_to_accountSettings)
    }

    fun logout() {
        ActivityHelper.startActivity(fragment.requireActivity(), LoginActivity::class.java)
        LocalAuthHelper.removeLoginInformation(fragment.requireContext())
    }

    fun toNotifications() {
        fragment.findNavController().navigate(R.id.action_navigation_profile_to_notificationSettings)
    }

    fun toPublicProfile() {
        fragment.findNavController().navigate(R.id.action_navigation_profile_to_publicProfileFragment)
    }

    fun login(view: View, model: User) {
        val context = fragment.requireContext()
        if (LocalAuthHelper.login(model, context)) {
            ActivityHelper.startActivity(fragment.requireActivity(), AppActivity::class.java)
        } else {
            (view.parent as ConstraintLayout).findViewById<EditText>(R.id.password).text = null
            Toast.makeText(context, context.getString(R.string.login_failed), Toast.LENGTH_LONG).show()
        }
    }

}