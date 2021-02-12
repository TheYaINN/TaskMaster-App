package de.taskmaster.activity.app.ui.profile

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import de.taskmaster.R
import de.taskmaster.activity.util.fragment.SubFragment
import de.taskmaster.databinding.FragmentProfilePublicBinding
import de.taskmaster.model.data.User

class ProfilePublicFragment : SubFragment<FragmentProfilePublicBinding>(R.layout.fragment_profile_public) {

    private lateinit var viewModel: User

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(User::class.java)
        binder.model = viewModel
    }
}