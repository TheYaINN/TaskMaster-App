package de.taskmaster.model.data.impl

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "address")
data class Address(

    @PrimaryKey(autoGenerate = true)
    var addressId: Int = -1,

    var userId: Int = -1,

    var name: String? = "",

    var street: String? = "",

    var number: Int? = -1,

    var zipCode: Int? = -1,

    var city: String? = "",

    var notifiable: Boolean = false
) {


    override fun toString(): String {
        return "$name: $street $number, $city $zipCode"
    }
}
