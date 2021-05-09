package de.taskmaster.model.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import de.taskmaster.model.data.impl.TodoListWithAssociations

@Dao
interface TodoListWithAssociationsDAO {

    @Query("SELECT * FROM tasks t WHERE t.taskId == :id")
    fun getByID(id: Int): LiveData<List<TodoListWithAssociations>>

}