package de.taskmaster.activity.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import de.taskmaster.R
import de.taskmaster.activity.app.ui.profile.settings.PlaceAdapter
import de.taskmaster.databinding.FragmentRegistrationBinding
import de.taskmaster.model.data.impl.Address
import de.taskmaster.model.data.impl.ObservableViewModel
import de.taskmaster.model.handler.AddressEditorHandler
import de.taskmaster.model.handler.PlaceEditor
import de.taskmaster.model.handler.RegistrationHandler

class RegisterFragment : Fragment(), PlaceEditor {

    private val viewModel = RegisterViewModel()
    private val placeAdapter = PlaceAdapter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val fragmentBinding = DataBindingUtil.inflate<FragmentRegistrationBinding>(inflater, R.layout.fragment_registration, container, false)
        fragmentBinding.model = viewModel
        fragmentBinding.handler = RegistrationHandler()
        fragmentBinding.addHandler = AddressEditorHandler(this, requireContext())

        val recyclerView = fragmentBinding.root.findViewById<RecyclerView>(R.id.items)
        recyclerView.adapter = placeAdapter

        placeAdapter.data

        return fragmentBinding.root
    }

    override fun add(address: Address) {
        viewModel.places.add(address)
        updateAdapter()
    }

    override fun remove(address: Address) {
        viewModel.places.remove(address)
        updateAdapter()
    }

    private fun updateAdapter() {
        placeAdapter.setData(viewModel.places)
    }

    override fun getView(id: Int): View {
        return requireView().findViewById(id)
    }

}

class RegisterViewModel : ObservableViewModel() {
    var firstName: String = ""
    var lastName: String = ""
    var userName: String = ""
    var password: String = ""
    var email: String = ""
    var places: MutableList<Address> = arrayListOf()

    fun clear() {
        firstName = ""
        lastName = ""
        userName = ""
        password = ""
        email = ""
        places = arrayListOf()
    }
}