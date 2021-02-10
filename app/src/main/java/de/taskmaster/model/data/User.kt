package de.taskmaster.model.data

class User {

    var username: String? = null

    var password: String? = null

    var firstname: String? = null

    var lastname: String? = null

    var email: String? = null

    val places: MutableList<Address> = arrayListOf()
}