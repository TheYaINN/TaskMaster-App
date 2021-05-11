package de.taskmaster.model.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import de.taskmaster.model.data.impl.GroupWithToDoLists

@Dao
interface GroupWithToDoListsDAO {

    @Transaction
    @Query("SELECT * FROM groups g  WHERE g.groupId == :id")
    fun getByID(id: Int): LiveData<GroupWithToDoLists>

}