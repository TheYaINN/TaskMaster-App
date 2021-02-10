package de.taskmaster.ui.app.profile.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import de.taskmaster.R
import de.taskmaster.databinding.FragmentProfileEditBinding
import de.taskmaster.model.data.Address
import de.taskmaster.model.handler.AddressEditorHandler
import de.taskmaster.model.handler.PlaceEditor
import de.taskmaster.model.model.UserViewModel
import de.taskmaster.ui.app.SubFragment

class AccountSettingsFragment : SubFragment(R.layout.fragment_profile_edit), PlaceEditor {

    private val userViewModel = UserViewModel()
    private val placeAdapter = PlaceAdapter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val fragmentBinding = DataBindingUtil.inflate<FragmentProfileEditBinding>(inflater, R.layout.fragment_profile_edit, container, false)
        fragmentBinding.model = userViewModel
        fragmentBinding.addHandler = AddressEditorHandler(this, requireContext())
        val recyclerView = fragmentBinding.root.findViewById<RecyclerView>(R.id.items)
        recyclerView.adapter = placeAdapter
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        userViewModel.user.observe(viewLifecycleOwner, { placeAdapter.setData(it.places as List<Address>) })
    }

    override fun add(address: Address) {
        userViewModel.user.addPlace(address)
    }

    override fun remove(address: Address) {
        userViewModel.user.removePlace(address)
    }

    override fun getView(id: Int): View {
        return requireView().findViewById(id)
    }

}
