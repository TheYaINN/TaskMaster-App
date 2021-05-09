package de.taskmaster.model.data.impl

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "address",
    foreignKeys = [
        ForeignKey(entity = User::class, parentColumns = arrayOf("userId"), childColumns = arrayOf("addressId"), onDelete = ForeignKey.CASCADE)
    ]
)
class Address {

    @PrimaryKey(autoGenerate = true)
    var addressId: Int = -1

    var name: String? = ""

    var street: String? = ""

    var number: Int? = -1

    var zipCode: Int? = -1

    var city: String? = ""

    var notifiable: Boolean = false

    override fun toString(): String {
        return "$name: $street $number, $city $zipCode"
    }
}
