package de.taskmaster.activity.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.Bindable
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import de.taskmaster.BR
import de.taskmaster.R
import de.taskmaster.databinding.FragmentLoginBinding
import de.taskmaster.model.data.impl.ObservableViewModel
import de.taskmaster.model.handler.NavigationHandler

class LoginFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val fragmentBinding = DataBindingUtil.inflate<FragmentLoginBinding>(inflater, R.layout.fragment_login, container, false)
        fragmentBinding.model = ViewModelProvider(requireActivity()).get(LoginViewModel::class.java)
        fragmentBinding.handler = NavigationHandler(this)
        return fragmentBinding.root
    }

}

class LoginViewModel : ObservableViewModel() {

    @get:Bindable
    @set:Bindable
    var userName: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.userName)
        }

    @get:Bindable
    @set:Bindable
    var password: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.password)
        }

    @get:Bindable
    @set:Bindable
    var rememberMe = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.rememberMe)
        }
}