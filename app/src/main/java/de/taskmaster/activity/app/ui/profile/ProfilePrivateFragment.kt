package de.taskmaster.activity.app.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import de.taskmaster.R
import de.taskmaster.activity.util.fragment.TopLevelFragment
import de.taskmaster.databinding.FragmentProfilePrivateBinding
import de.taskmaster.model.data.impl.User
import de.taskmaster.model.handler.NavigationHandler

class ProfilePrivateFragment : TopLevelFragment(R.layout.fragment_profile_private, null) {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binder = DataBindingUtil.inflate<FragmentProfilePrivateBinding>(inflater, R.layout.fragment_profile_private, container, false)
        binder.handler = NavigationHandler(this)
        binder.model = ViewModelProvider(this).get(ProfilePrivateViewModel::class.java)
        return binder.root
    }
}

class ProfilePrivateViewModel : ViewModel() {
    val user: LiveData<User> = MutableLiveData()

    fun getDisplayableName(): String {
        return "${user.value?.firstName} ${user.value?.lastName}"
    }
}