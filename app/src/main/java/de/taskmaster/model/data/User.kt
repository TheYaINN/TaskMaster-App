package de.taskmaster.model.data

class User(val username: String, val password: String) {

    var firstname: String? = null

    var lastname: String? = null

    var email: String? = null

    var homeAddress: Address? = null

    var workAddress: String? = null
}