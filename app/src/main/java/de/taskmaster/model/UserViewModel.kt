package de.taskmaster.model

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import de.taskmaster.model.data.Group
import de.taskmaster.model.data.ObservableViewModel

class UserViewModel : ObservableViewModel() {

    private val _avatar = MutableLiveData<Bitmap>()
    val avatar: LiveData<Bitmap> = _avatar

    private val _username = MutableLiveData<String>()
    val username: LiveData<String> = _username

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _firstName = MutableLiveData<String>()
    val firstName: LiveData<String> = _firstName

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _groups = MutableLiveData<List<Group>>()
    val groups: LiveData<List<Group>> = _groups

    private val _lists = MutableLiveData<List<Group>>()
    val lists: LiveData<List<Group>> = _lists

    private val _places = MutableLiveData<List<Group>>()
    val places: LiveData<List<Group>> = _places

    val rememberMe = MutableLiveData(false)

}