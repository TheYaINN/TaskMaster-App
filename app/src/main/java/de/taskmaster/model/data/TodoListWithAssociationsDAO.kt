package de.taskmaster.model.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import de.taskmaster.model.data.impl.TodoListWithAssociations

@Dao
interface TodoListWithAssociationsDAO {

    @Query("SELECT * FROM todolists t WHERE userId == :userId")
    fun getByUserId(userId: Int): LiveData<List<TodoListWithAssociations>>

}