package de.taskmaster.model.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import de.taskmaster.model.data.impl.Group

@Dao
interface GroupDAO {

    @Insert
    suspend fun insert(user: Group)

    @Update
    suspend fun update(user: Group)

    @Delete
    suspend fun delete(user: Group)

    @Query("SELECT * FROM groups g WHERE g.groupId == :userId")
    fun getGroupsByUserId(userId: Int): LiveData<List<Group>>

    @Query("SELECT * FROM groups g WHERE g.groupId == :groupId")
    suspend fun getGroupByGroupId(groupId: Int): Group?


}