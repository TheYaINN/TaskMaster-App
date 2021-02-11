package de.taskmaster.model.data

class User : ObservableViewModel() {

    var username: String? = null

    var password: String? = null

    var firstName: String? = null

    var lastName: String? = null

    var email: String? = null

    var rememberMe: Boolean = false

    var groups: List<Group> = arrayListOf()

    val places: MutableList<Address> = arrayListOf()
}