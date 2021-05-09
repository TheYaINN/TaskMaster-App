package de.taskmaster.model.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import de.taskmaster.model.data.impl.Address

@Dao
interface AddressDAO {

    @Insert
    suspend fun insert(toDoList: Address)

    @Update
    suspend fun update(toDoList: Address)

    @Delete
    suspend fun delete(toDoList: Address)
}