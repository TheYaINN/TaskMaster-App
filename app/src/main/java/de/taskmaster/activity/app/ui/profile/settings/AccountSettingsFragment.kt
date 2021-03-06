package de.taskmaster.activity.app.ui.profile.settings

import android.app.Application
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import de.taskmaster.R
import de.taskmaster.activity.util.fragment.SubFragment
import de.taskmaster.databinding.FragmentProfileEditBinding
import de.taskmaster.db.LocalDataBaseConnector
import de.taskmaster.model.data.impl.Address
import de.taskmaster.model.data.impl.Displayable
import de.taskmaster.model.data.impl.UserWithAssociations
import de.taskmaster.model.handler.AddressEditorHandler
import de.taskmaster.model.handler.NavigationHandler
import de.taskmaster.model.handler.PlaceEditor
import de.taskmaster.model.rotate
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AccountSettingsFragment : SubFragment<FragmentProfileEditBinding>(R.layout.fragment_profile_edit), PlaceEditor {

    private lateinit var viewModel: AccountSettingsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this, AccountSettingsViewModelFactory(requireActivity().application, userId, viewLifecycleOwner))
            .get(AccountSettingsViewModel::class.java)
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
        viewModel.userWithAssociations.observe(viewLifecycleOwner, { data -> placeAdapter.setData(data.places) })
    }

    override fun add(address: Address) {
        viewModel.addAddress(address)
    }

    override fun remove(address: Address) {
        viewModel.removeAddress(address)
    }

    override fun getView(id: Int): View {
        return requireView().findViewById(id)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE_SELECT_AVATAR && resultCode == AppCompatActivity.RESULT_OK && null != data) {
            val selectedImage = data.data
            val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
            val cursor = requireActivity().contentResolver.query(selectedImage!!, filePathColumn, null, null, null)
            cursor!!.moveToFirst()
            val columnIndex = cursor.getColumnIndex(filePathColumn[0])
            val picturePath = cursor.getString(columnIndex)
            cursor.close()
            //Has to be rotated by 90 degrees CW at this point, due to importing it this way causes it to be rotated 90 degrees CCW
            viewModel.updateImage(BitmapFactory.decodeFile(picturePath).rotate(90f))
        }
    }

    override fun save(): Boolean {
        val user = viewModel.userWithAssociations.value!!.user
        GlobalScope.launch {
            LocalDataBaseConnector.instance.userDAO.update(user)
        }
        return super.save()
    }

}

class AccountSettingsViewModel(userId: Int, viewLifecycleOwner: LifecycleOwner) : Displayable() {

    private var _user: MutableLiveData<UserWithAssociations> = MutableLiveData()
    val userWithAssociations: LiveData<UserWithAssociations> = _user

    init {
        viewModelScope.launch {
            LocalDataBaseConnector.instance.userWithAssociationsDAO.getByUserId(userId).observe(viewLifecycleOwner, { _user.postValue(it) })
        }
    }

    fun deleteAccount() {
        GlobalScope.launch {
            LocalDataBaseConnector.instance.userDAO.delete(userWithAssociations.value!!.user)
        }
    }

    fun addAddress(address: Address) {
        GlobalScope.launch {
            val tempUser = userWithAssociations.value!!
            tempUser.places.toMutableList().remove(address)
            _user.postValue(tempUser)
            LocalDataBaseConnector.instance.addressDAO.insert(address)
        }
    }

    fun removeAddress(address: Address) {
        GlobalScope.launch {
            val tempUser = userWithAssociations.value!!
            tempUser.places.toMutableList().add(address)
            _user.postValue(tempUser)
            LocalDataBaseConnector.instance.addressDAO.delete(address)
        }
    }
}

class AccountSettingsViewModelFactory(application: Application, private val userId: Int, private val viewLifecycleOwner: LifecycleOwner) :
    ViewModelProvider.AndroidViewModelFactory(application) {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AccountSettingsViewModel(userId, viewLifecycleOwner) as T
    }

}