package de.taskmaster.ui.app.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import de.taskmaster.R
import de.taskmaster.databinding.FragmentProfileEditBinding
import de.taskmaster.model.binding.ToggleEditableComponentHandler
import de.taskmaster.ui.app.SubFragment

class AccountSettingsFragment : SubFragment(R.layout.fragment_profile_edit) {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val fragmentBinding = DataBindingUtil.inflate<FragmentProfileEditBinding>(inflater, R.layout.fragment_profile_edit, container, false)
        fragmentBinding.model = UserViewModel()
        fragmentBinding.presenter = ToggleEditableComponentHandler(requireContext())
        return fragmentBinding.root
    }

}
