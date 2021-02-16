package de.taskmaster.activity.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import de.taskmaster.R
import de.taskmaster.databinding.FragmentLoginBinding
import de.taskmaster.model.UserViewModel
import de.taskmaster.model.data.User
import de.taskmaster.model.handler.NavigationHandler

class LoginFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val fragmentBinding = DataBindingUtil.inflate<FragmentLoginBinding>(inflater, R.layout.fragment_login, container, false)
        fragmentBinding.model = ViewModelProvider(requireActivity()).get(User::class.java)
        fragmentBinding.handler = NavigationHandler(this)
        return fragmentBinding.root
    }

}