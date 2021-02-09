package de.taskmaster.ui.app.profile.notification

import android.location.Address
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NotificationViewModel : ViewModel() {

    private val _places = MutableLiveData<List<Address>>()
    val places: LiveData<List<Address>> = _places

}