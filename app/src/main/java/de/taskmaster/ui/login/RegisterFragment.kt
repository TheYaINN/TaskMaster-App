package de.taskmaster.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import de.taskmaster.R
import de.taskmaster.databinding.FragmentRegistrationBinding
import de.taskmaster.model.binding.AddressEditorHandler
import de.taskmaster.model.binding.PlaceEditor
import de.taskmaster.model.binding.RegistrationHandler
import de.taskmaster.model.data.Address
import de.taskmaster.model.model.UserViewModel
import de.taskmaster.ui.app.profile.settings.PlaceAdapter

class RegisterFragment : Fragment(), PlaceEditor {

    private val userViewModel = UserViewModel()
    private val placeAdapter = PlaceAdapter(this)

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
        userViewModel.user.observe(viewLifecycleOwner, { placeAdapter.setData(it.places) })
    }

    override fun add(address: Address) {
        userViewModel.addPlace(address)
    }

    override fun remove(address: Address) {
        userViewModel.removePlace(address)
    }

    override fun getView(id: Int): View {
        return requireView().findViewById(id)
    }

}
