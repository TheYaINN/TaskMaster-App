package de.taskmaster.model.data.impl

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "usr")
data class User(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "userId")
    val userId: Int,

    @ColumnInfo(name = "profilePicture")
    var profilePicture: Bitmap?,

    @ColumnInfo(name = "userName")
    var username: String,

    @ColumnInfo(name = "password")
    var password: String,

    @ColumnInfo(name = "salt")
    var salt: String,

    @ColumnInfo(name = "iterations")
    var iterations: Int,

    @ColumnInfo(name = "firstname")
    var firstName: String,

    @ColumnInfo(name = "lastname")
    var lastName: String,

    @ColumnInfo(name = "email")
    var email: String,
)

data class UserWithAssociations(

    @Embedded val user: User,

    @Relation(parentColumn = "userId", entityColumn = "groupId", entity = Group::class)
    val groups: List<Group>,

    @Relation(parentColumn = "userId", entityColumn = "listId", entity = ToDoList::class)
    val lists: List<ToDoList>,

    @Relation(parentColumn = "userId", entityColumn = "addressId", entity = Address::class)
    val places: List<Address>,
)