package de.taskmaster.model.handler

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import de.taskmaster.R
import de.taskmaster.activity.login.LoginActivity
import de.taskmaster.activity.util.ActivityHelper
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

    fun toPublicProfile() {
        fragment.findNavController().navigate(R.id.action_navigation_profile_to_publicProfileFragment)
    }

}