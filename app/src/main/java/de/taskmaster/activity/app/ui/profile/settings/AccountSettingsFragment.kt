package de.taskmaster.activity.app.ui.profile.settings

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import de.taskmaster.R
import de.taskmaster.activity.util.fragment.SubFragment
import de.taskmaster.databinding.FragmentProfileEditBinding
import de.taskmaster.model.data.impl.Address
import de.taskmaster.model.data.impl.Displayable
import de.taskmaster.model.data.impl.ObservableViewModel
import de.taskmaster.model.handler.AddressEditorHandler
import de.taskmaster.model.handler.NavigationHandler
import de.taskmaster.model.handler.PlaceEditor

class AccountSettingsFragment : SubFragment<FragmentProfileEditBinding>(R.layout.fragment_profile_edit), PlaceEditor {

    private lateinit var viewModel: AccountSettingsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(AccountSettingsViewModel::class.java)
        binder.model = viewModel
        binder.addHandler = AddressEditorHandler(this, requireContext())
        binder.handler = NavigationHandler(this)

        binder.root.findViewById<Button>(R.id.delete_account).setOnClickListener {
            viewModel.deleteAccount()
            NavigationHandler(this).logout()
        }

        val recyclerView = binder.root.findViewById<RecyclerView>(R.id.items)
        val placeAdapter = PlaceAdapter(this)
        recyclerView.adapter = placeAdapter
    }

    override fun add(address: Address) {
        //TODO
    }

    override fun remove(address: Address) {
        //TODO
    }

    override fun getView(id: Int): View {
        return requireView().findViewById(id)
    }

}

class AccountSettingsViewModel : ObservableViewModel(), Displayable {

    var firstName: String = ""
    var lastName: String = ""
    var email: String = ""

    fun deleteAccount() {

    }

    override fun getImage(): Bitmap? {
        return null
    }

    override fun rotate() {

    }

}
