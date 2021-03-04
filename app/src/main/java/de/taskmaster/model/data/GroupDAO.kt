package de.taskmaster.model.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import de.taskmaster.model.data.impl.Group

@Dao
interface GroupDAO {

    @Insert
    fun insert(user: Group)

    @Update
    fun update(user: Group)

    @Delete
    fun delete(user: Group)

}