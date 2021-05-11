package de.taskmaster.model.data.impl

import android.graphics.Bitmap
import androidx.room.*

@Entity(tableName = "usr")
data class User(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "userId")
    val userId: Int = 0,

    @ColumnInfo(name = "profilePicture")
    var profilePicture: Bitmap? = null,

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

@Entity(primaryKeys = ["userId", "gorupId"])
data class UserGroupCrossRef(
    var userId: Int,

    var groupId: Int
)

data class UserWithAssociations(

    @Embedded val user: User,

    @Relation(parentColumn = "userId", entityColumn = "groupId", associateBy = Junction(UserGroupCrossRef::class))
    val groups: List<Group>,

    @Relation(parentColumn = "userId", entityColumn = "userId")
    val lists: List<ToDoList>,

    @Relation(parentColumn = "userId", entityColumn = "userId")
    val places: List<Address>,
)