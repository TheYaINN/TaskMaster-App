package de.taskmaster.model.data.impl

import android.graphics.Bitmap
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(

    @PrimaryKey(autoGenerate = true)
    val taskId: Int,

    val listId: Int,

    var img: Bitmap?,

    var title: String,

    var description: String,

    var status: Status,

    @Embedded
    var responsiblePerson: User? = null,
)