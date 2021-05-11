package de.taskmaster.model.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import de.taskmaster.model.data.impl.Task

@Dao
interface TaskDAO {

    @Insert
    suspend fun insert(user: Task)

    @Update
    suspend fun update(user: Task)

    @Delete
    suspend fun delete(user: Task)

    @Query("SELECT * FROM tasks t JOIN usr u ON :id == t.taskId")
    fun getByID(id: Int): LiveData<Task>
}