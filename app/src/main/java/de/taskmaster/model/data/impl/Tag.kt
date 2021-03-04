package de.taskmaster.model.data.impl

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tags")
data class Tag(

    @PrimaryKey(autoGenerate = true)
    var tagId: Int,

    var value: String
)
