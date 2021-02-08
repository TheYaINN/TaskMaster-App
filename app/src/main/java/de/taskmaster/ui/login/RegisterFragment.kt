package de.taskmaster.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import de.taskmaster.R
import de.taskmaster.databinding.FragmentRegistrationBinding
import de.taskmaster.model.binding.ToggleEditableComponentHandler

class RegisterFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val fragmentBinding = DataBindingUtil.inflate<FragmentRegistrationBinding>(inflater, R.layout.fragment_registration, container, false)
        fragmentBinding.presenter = ToggleEditableComponentHandler(requireContext())
        return fragmentBinding.root
    }

}
