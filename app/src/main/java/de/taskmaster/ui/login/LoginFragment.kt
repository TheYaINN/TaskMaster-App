package de.taskmaster.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import de.taskmaster.R
import de.taskmaster.databinding.FragmentLoginBinding
import de.taskmaster.model.handler.LoginHandler

class LoginFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val fragmentBinding = DataBindingUtil.inflate<FragmentLoginBinding>(inflater, R.layout.fragment_login, container, false)
        fragmentBinding.model = LoginViewModel()
        fragmentBinding.handler = LoginHandler(requireContext(), requireActivity())
        return fragmentBinding.root
    }


}