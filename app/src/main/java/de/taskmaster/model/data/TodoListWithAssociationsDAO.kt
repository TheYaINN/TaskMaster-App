package de.taskmaster.model.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import de.taskmaster.model.data.impl.TodoListWithAssociations

@Dao
interface TodoListWithAssociationsDAO {

    @Query("SELECT listId, title, description, deadline FROM todolists t JOIN usr u ON :id == t.listId ")
    fun getByID(id: Int): LiveData<List<TodoListWithAssociations>>

}