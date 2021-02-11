package de.taskmaster.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import de.taskmaster.R
import de.taskmaster.databinding.FragmentRegistrationBinding
import de.taskmaster.model.data.Address
import de.taskmaster.model.data.User
import de.taskmaster.model.handler.AddressEditorHandler
import de.taskmaster.model.handler.PlaceEditor
import de.taskmaster.model.handler.RegistrationHandler
import de.taskmaster.ui.app.profile.settings.PlaceAdapter

class RegisterFragment : Fragment(), PlaceEditor {

    private val userViewModel = User()
    private val placeAdapter = PlaceAdapter(this)
    private val observablePlaces = MutableLiveData(userViewModel.places)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val fragmentBinding = DataBindingUtil.inflate<FragmentRegistrationBinding>(inflater, R.layout.fragment_registration, container, false)
        fragmentBinding.model = userViewModel
        fragmentBinding.handler = RegistrationHandler()
        fragmentBinding.addHandler = AddressEditorHandler(this, requireContext())

        val recyclerView = fragmentBinding.root.findViewById<RecyclerView>(R.id.items)
        recyclerView.adapter = placeAdapter

        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observablePlaces.observe(viewLifecycleOwner, { placeAdapter.setData(it as List<Address>) })
    }

    override fun add(address: Address) {
        userViewModel.places.add(address)
        observablePlaces.value = userViewModel.places
    }

    override fun remove(address: Address) {
        userViewModel.places.remove(address)
        observablePlaces.value = userViewModel.places
    }

    override fun getView(id: Int): View {
        return requireView().findViewById(id)
    }

}
