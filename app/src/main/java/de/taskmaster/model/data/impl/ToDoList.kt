package de.taskmaster.model.data.impl

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(foreignKeys = [ForeignKey(entity = User::class, parentColumns = arrayOf("userId"), childColumns = arrayOf("listId"), onDelete = CASCADE)])
data class ToDoList(

    @PrimaryKey(autoGenerate = true)
    var listId: Int,

    var title: String,

    var description: String,

    @Embedded
    val place: Address,

    var deadline: Deadline = Deadline(null),
)

data class TodoListWithAssociations(

    @Embedded val list: ToDoList,

    @Relation(parentColumn = "listId", entityColumn = "taskId")
    val tasks: List<Task>,

    @Relation(parentColumn = "listId", entityColumn = "tagId")
    val tags: List<Tag>
)