package de.taskmaster.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

@Dao
interface UserDAO {

    @Insert
    fun insert()

    @Update
    fun update()

    @Delete
    fun delete()

}