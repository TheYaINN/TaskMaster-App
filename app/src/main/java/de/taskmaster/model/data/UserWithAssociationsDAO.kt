package de.taskmaster.model.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import de.taskmaster.model.data.impl.UserWithAssociations

@Dao
interface UserWithAssociationsDAO {

    @Query("SELECT * FROM usr u WHERE u.userId == :id")
    fun getByID(id: Int): LiveData<UserWithAssociations>

    /* @Query("SELECT * FROM groups WHERE groupId IS :id")
     fun getGroupsByUserID(id: Int): LiveData<List<Group>>

     @Query("SELECT * FROM usr u WHERE u.userId == :id")
     fun getListsByUserID(id: Int): LiveData<List<ToDoList>>*/

}