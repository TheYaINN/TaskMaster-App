package de.taskmaster.ui.app.group.editor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GroupsEditorViewModel : ViewModel() {

    private val _name = MutableLiveData<String>().apply {
        value = "Gruppen"
    }
    val name: LiveData<String> = _name

    private val _description = MutableLiveData<String>().apply {
        value = "Gruppen"
    }
    val description: LiveData<String> = _description

    private val _administrators = MutableLiveData<String>().apply {
        value = "Gruppen"
    }
    val administrators: LiveData<String> = _administrators

    private val _members = MutableLiveData<String>().apply {
        value = "Gruppen"
    }
    val members: LiveData<String> = _members

    private val _lists = MutableLiveData<String>().apply {
        value = "Gruppen"
    }
    val text: LiveData<String> = _lists

}