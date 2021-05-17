package de.taskmaster.model.data

import androidx.lifecycle.LiveData
import androidx.room.*
import de.taskmaster.model.data.impl.Task

@Dao
interface TaskDAO {

    @Insert
    suspend fun insert(user: Task)

    @Update
    suspend fun update(user: Task)

    @Delete
    suspend fun delete(user: Task)

    @Query("SELECT * FROM tasks t WHERE t.listId = :listId")
    fun getByListId(listId: Int): LiveData<List<Task>>
}