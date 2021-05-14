package de.taskmaster.model.data.impl

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity(tableName = "todolists")
data class ToDoList(

    @PrimaryKey(autoGenerate = true)
    var listId: Int = 0,

    var userId: Int = 0,

    var groupId: Int = 0,

    var title: String = "",

    var description: String = "",

    var addressId: Int = 0,

    var deadline: Deadline = Deadline(null),
)

data class TodoListWithAssociations(

    @Embedded val list: ToDoList,

    @Relation(parentColumn = "listId", entityColumn = "listId")
    val tasks: List<Task>,

    @Relation(parentColumn = "listId", entityColumn = "tagId")
    val tags: List<Tag>

)