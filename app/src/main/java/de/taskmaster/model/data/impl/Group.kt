package de.taskmaster.model.data.impl

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "groups")
data class Group(

    @PrimaryKey(autoGenerate = true)
    var groupId: Int = 0,

    var title: String,

    var description: String,
) {
    companion object {
        val EMPTY = Group(title = "", description = "")
    }
}


data class GroupWithToDoLists(

    @Embedded val group: Group,

    @Relation(parentColumn = "groupId", entityColumn = "groupId")
    val list: List<ToDoList>,
)