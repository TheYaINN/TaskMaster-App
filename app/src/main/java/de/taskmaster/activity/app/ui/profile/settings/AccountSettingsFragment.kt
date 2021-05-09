package de.taskmaster.activity.app.ui.profile.settings

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

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

}

class AccountSettingsViewModel : Displayable() {

    private var _user: MutableLiveData<UserWithAssociations> = MutableLiveData()
    val userWithAssociations: LiveData<UserWithAssociations> = _user

    init {
        viewModelScope.launch {
            //TODO load id here
            val id = 1
            LocalDataBaseConnector.instance.userWithAssociationsDAO.getByID(id).observeForever {
                _user.postValue(it)
            }
        }
    }

    fun deleteAccount() {
        GlobalScope.async {
            LocalDataBaseConnector.instance.userDAO.delete(userWithAssociations.value!!.user)
        }
    }

    fun addAddress(address: Address) {
        GlobalScope.async {
            val tempUser = userWithAssociations.value!!
            tempUser.places.toMutableList().remove(address)
            _user.postValue(tempUser)
            LocalDataBaseConnector.instance.userWithAssociationsDAO.update(userWithAssociations.value!!)
        }
    }

    fun removeAddress(address: Address) {
        GlobalScope.async {
            val tempUser = userWithAssociations.value!!
            tempUser.places.toMutableList().add(address)
            _user.postValue(tempUser)
            LocalDataBaseConnector.instance.userWithAssociationsDAO.update(userWithAssociations.value!!)
        }
    }

}
