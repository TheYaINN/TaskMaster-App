package de.taskmaster.db

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import de.taskmaster.model.data.impl.Deadline
import de.taskmaster.model.data.impl.Status
import java.io.ByteArrayOutputStream
import java.time.LocalDate

class DBConverter {

    @TypeConverter
    fun fromBitmap(bmp: Bitmap?): ByteArray? {
        if (bmp == null) {
            return ByteArray(0)
        }
        val bos = ByteArrayOutputStream()
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, bos)
        return bos.toByteArray()
    }

    @TypeConverter
    fun toBitmap(img: ByteArray): Bitmap? {
        return BitmapFactory.decodeByteArray(img, 0, img.size)
    }

    @TypeConverter
    fun fromStatus(status: Status): String {
        return status.toString()
    }

    @TypeConverter
    fun toStatus(status: String): Status {
        return Status.valueOf(status)
    }

    @TypeConverter
    fun fromDeadline(deadline: Deadline): String {
        return deadline.date.toString()
    }

    @TypeConverter
    fun toDeadline(deadline: String): Deadline {
        if (deadline == "null") {
            return Deadline(null)
        }
        return Deadline(LocalDate.parse(deadline))
    }

}
