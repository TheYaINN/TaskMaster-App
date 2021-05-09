package de.taskmaster.model.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import de.taskmaster.model.data.impl.ToDoList

@Dao
interface ToDoListDAO {

    @Insert
    suspend fun insert(toDoList: ToDoList)

    @Update
    suspend fun update(toDoList: ToDoList)

    @Delete
    suspend fun delete(toDoList: ToDoList)

    @Query("SELECT * FROM todolists t JOIN usr u ON :id == t.listId")
    fun getByID(id: Int): LiveData<List<ToDoList>>

}