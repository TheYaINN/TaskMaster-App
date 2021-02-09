package de.taskmaster.ui.app.profile.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import de.taskmaster.R
import de.taskmaster.databinding.FragmentProfileEditBinding
import de.taskmaster.model.binding.AddressEditorHandler
import de.taskmaster.model.binding.PlaceEditor
import de.taskmaster.model.data.Address
import de.taskmaster.ui.app.SubFragment
import de.taskmaster.ui.app.profile.UserViewModel

class AccountSettingsFragment : SubFragment(R.layout.fragment_profile_edit), PlaceEditor {

    private val userViewModel = UserViewModel()
    private val placeAdapter = PlaceAdapter()

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
        //TODO: observe places in userViewModel
        placeAdapter.setData(userViewModel.user.places)
    }

    override fun add(address: Address) {
        //FIXME: should not be accessed this way
        userViewModel.user.places.add(address)
    }

    override fun getView(id: Int): View {
        return requireView().findViewById(id)
    }

}
