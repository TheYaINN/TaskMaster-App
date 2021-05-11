package de.taskmaster.activity.app.ui.profile

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import de.taskmaster.R
import de.taskmaster.activity.util.fragment.TopLevelFragment
import de.taskmaster.databinding.FragmentProfilePrivateBinding
import de.taskmaster.db.LocalDataBaseConnector
import de.taskmaster.model.data.impl.User
import de.taskmaster.model.handler.NavigationHandler
import kotlinx.coroutines.launch

class ProfilePrivateFragment : TopLevelFragment(R.layout.fragment_profile_private, null) {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binder = DataBindingUtil.inflate<FragmentProfilePrivateBinding>(inflater, R.layout.fragment_profile_private, container, false)
        binder.handler = NavigationHandler(this)

        binder.model = ViewModelProvider(this, ProfilePrivateViewModelFactory(requireActivity().application, userId))
            .get(ProfilePrivateViewModel::class.java)
        return binder.root
    }
}

class ProfilePrivateViewModel(userId: Int) : ViewModel() {

    var user: User? = null

    init {
        viewModelScope.launch {
            user = LocalDataBaseConnector.instance.userDAO.getByID(userId)
        }
    }

    fun getDisplayableName(): String {
        return if (user != null) "${user?.firstName} ${user?.lastName}" else ""
    }
}

class ProfilePrivateViewModelFactory(application: Application, private val userId: Int) :
    ViewModelProvider.AndroidViewModelFactory(application) {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfilePrivateViewModel(userId) as T
    }

}
