package de.taskmaster.ui.app.profile

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import de.taskmaster.BuildConfig
import de.taskmaster.R
import de.taskmaster.auth.LocalAuthHelper
import de.taskmaster.ui.app.TopLevelFragment
import de.taskmaster.ui.login.LoginActivity


class ProfileFragment : TopLevelFragment(R.layout.fragment_profile_private) {

    private lateinit var profileViewModel: ProfileViewModel
    //TODO: implment this somehow profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<Button>(R.id.account_settings).setOnClickListener {
            findNavController().navigate(R.id.action_navigation_profile_to_accountSettings)
        }
        view.findViewById<Button>(R.id.sign_out).setOnClickListener {
            LocalAuthHelper.removeLoginInformation(requireContext())
            startActivity(Intent(requireContext(), LoginActivity::class.java))
            requireActivity().finish()
        }
        view.findViewById<Button>(R.id.notifications).setOnClickListener {
            findNavController().navigate(R.id.action_navigation_profile_to_notificationSettings)
        }
        view.findViewById<TextView>(R.id.visit_profile).setOnClickListener {
            findNavController().navigate(R.id.action_navigation_profile_to_publicProfileFragment)
        }

        view.findViewById<TextView>(R.id.version).text =
            "VERSION ${BuildConfig.VERSION_CODE} (${BuildConfig.VERSION_NAME})"

    }

}