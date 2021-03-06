package de.taskmaster.model.handler

import android.content.Intent
import android.provider.MediaStore
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import de.taskmaster.R
import de.taskmaster.activity.app.AppActivity
import de.taskmaster.activity.login.LoginActivity
import de.taskmaster.activity.login.LoginViewModel
import de.taskmaster.activity.util.ActivityHelper
import de.taskmaster.activity.util.StorageHelper
import de.taskmaster.auth.LocalAuthHelper


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

    fun toPublicProfile(userId: Int) {
        fragment.findNavController().navigate(R.id.action_navigation_profile_to_publicProfileFragment, bundleOf("userId" to userId))
    }

    fun login(view: View, model: LoginViewModel) {
        val context = fragment.requireContext()
        if (LocalAuthHelper.login(model, context)) {
            ActivityHelper.startActivity(fragment.requireActivity(), AppActivity::class.java)
        } else {
            (view.parent as ConstraintLayout).findViewById<EditText>(R.id.password).text = null
            Toast.makeText(context, context.getString(R.string.login_failed), Toast.LENGTH_LONG).show()
        }
    }

    fun takePicture() {
        StorageHelper.verifyStoragePermissions(fragment.requireActivity())
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        fragment.startActivityForResult(intent, 101)
    }

}