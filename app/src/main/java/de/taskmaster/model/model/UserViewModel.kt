package de.taskmaster.model.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.taskmaster.model.data.Address
import de.taskmaster.model.data.User

class UserViewModel : ViewModel() {

    fun addPlace(address: Address) {
        val user: User = _user.value ?: error("null user")
        user.places.add(address)
        _user.postValue(user)
    }

    fun removePlace(address: Address) {
        val user: User = _user.value ?: error("null user")
        user.places.remove(address)
        _user.postValue(user)
    }

    private val _user = MutableLiveData(User())
    val user: LiveData<User> = _user


}
