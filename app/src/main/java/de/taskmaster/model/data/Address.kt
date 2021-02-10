package de.taskmaster.model.data

class Address : Validator {

    var name: String? = null

    var street: String? = null

    var number: Int? = null

    var zipCode: Int? = null

    var city: String? = null

    var notifiable: Boolean = false

    override fun isValid(): Boolean {
        return name != null && street != null && number != null && zipCode != null && city != null
    }

    override fun toString(): String {
        return "$name: $street $number, $city $zipCode"
    }
}
