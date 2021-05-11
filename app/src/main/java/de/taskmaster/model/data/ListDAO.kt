package de.taskmaster.model.data

import androidx.lifecycle.LiveData
import androidx.room.*
import de.taskmaster.model.data.impl.ToDoList

@Dao
interface ToDoListDAO {

    @Insert
    suspend fun insert(toDoList: ToDoList)

    @Update
    suspend fun update(toDoList: ToDoList)

    @Delete
    suspend fun delete(toDoList: ToDoList)

    @Query("SELECT * FROM todolists t WHERE t.userId == :userId")
    fun getByUserId(userId: Int): LiveData<List<ToDoList>>

    @Query("SELECT * FROM todolists t WHERE t.listId == :listId")
    suspend fun getByListId(listId: Int): ToDoList?

}