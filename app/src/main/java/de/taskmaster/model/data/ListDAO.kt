package de.taskmaster.model.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import de.taskmaster.model.data.impl.ToDoList

@Dao
interface ListDAO {

    @Insert
    suspend fun insert(user: ToDoList)

    @Update
    suspend fun update(user: ToDoList)

    @Delete
    suspend fun delete(user: ToDoList)

    @Query("SELECT * FROM todolist t JOIN usr u ON :id == t.listId")
    fun getByID(id: Int): LiveData<ToDoList>

}