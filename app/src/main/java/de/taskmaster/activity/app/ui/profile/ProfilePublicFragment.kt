package de.taskmaster.activity.app.ui.profile

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import de.taskmaster.R
import de.taskmaster.activity.util.fragment.SubFragment
import de.taskmaster.databinding.FragmentProfilePublicBinding

class ProfilePublicFragment : SubFragment<FragmentProfilePublicBinding>(R.layout.fragment_profile_public, null) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binder.model = ViewModelProvider(this).get(ProfilePrivateViewModel::class.java)
    }
}