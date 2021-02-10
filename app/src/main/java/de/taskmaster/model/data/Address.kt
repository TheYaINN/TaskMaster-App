package de.taskmaster.model.data

class Address {

    var street: String? = "Am Ende"

    var number: Int? = 6

    var zipCode: Int? = 12345

    var city: String? = "Köllefornia"

    val notifiable: Boolean = false

    override fun toString(): String {
        return "$street $number, $city $zipCode"
    }
}
