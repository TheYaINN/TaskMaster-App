package de.taskmaster.ui.app.profile.settings

import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import de.taskmaster.R
import de.taskmaster.databinding.FragmentProfileEditBinding
import de.taskmaster.model.data.Address
import de.taskmaster.model.data.User
import de.taskmaster.model.handler.AddressEditorHandler
import de.taskmaster.model.handler.PlaceEditor
import de.taskmaster.ui.app.SubFragment

class AccountSettingsFragment : SubFragment<FragmentProfileEditBinding>(R.layout.fragment_profile_edit), PlaceEditor {

    private lateinit var userViewModel: User
    private lateinit var observablePlaces: MutableLiveData<List<Address>>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        userViewModel = ViewModelProvider(requireActivity()).get(User::class.java)
        observablePlaces = MutableLiveData(userViewModel.places)

        binder.model = userViewModel
        binder.addHandler = AddressEditorHandler(this, requireContext())

        val recyclerView = binder.root.findViewById<RecyclerView>(R.id.items)
        val placeAdapter = PlaceAdapter(this)
        recyclerView.adapter = placeAdapter
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

    override fun save(): Boolean {
        //TODO
        println("SAVING User: $userViewModel")
        return super.save()
    }

}
