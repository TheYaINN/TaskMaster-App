package de.taskmaster.model.data

open class User {

    var username: String? = null

    var password: String? = null

    var firstName: String? = null

    var lastName: String? = null

    var email: String? = null

    val places: MutableList<Address> = arrayListOf()
}