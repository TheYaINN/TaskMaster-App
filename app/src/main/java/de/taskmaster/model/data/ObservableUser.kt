package de.taskmaster.model.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ObservableUser(user: User) : LiveData<User>() {

    private val _username = MutableLiveData<String>(user.username)
    val username = _username

    private val _password = MutableLiveData<String>(user.password)
    val password = _password

    private val _firstName = MutableLiveData<String>(user.firstName)
    val firstName = _firstName

    private val _lastName = MutableLiveData<String>(user.lastName)
    val lastName = _lastName

    private val _email = MutableLiveData<String>(user.email)
    val email = _email

    private val _places = MutableLiveData(user.places)
    val places = _places

    fun addPlace(address: Address) {
        val list = _places.value ?: error("null places")
        list.add(address)
        _places.postValue(list)
    }

    fun removePlace(address: Address) {
        val list = _places.value ?: error("null places")
        list.remove(address)
        _places.postValue(list)
    }

}