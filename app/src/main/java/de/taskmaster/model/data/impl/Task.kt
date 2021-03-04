package de.taskmaster.model.data.impl

import android.graphics.Bitmap
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import de.taskmaster.model.rotate

@Entity(tableName = "tasks")
data class Task(

    @PrimaryKey(autoGenerate = true)
    val taskId: Int,

    var img: Bitmap?,

    var title: String,

    var description: String,

    var status: Status,

    @Embedded
    var responsiblePerson: User? = null
) : Displayable {

    override fun getImage(): Bitmap? {
        return img
    }

    override fun rotate() {
        img?.rotate(90f)
    }

}
