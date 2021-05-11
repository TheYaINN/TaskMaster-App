package de.taskmaster.model.data

import androidx.lifecycle.LiveData
import androidx.room.*
import de.taskmaster.model.data.impl.UserGroupCrossRef

@Dao
interface UserGroupCrossRefDAO {

    @Insert
    suspend fun insert(userGroupCrossRef: UserGroupCrossRef)

    @Update
    suspend fun update(userGroupCrossRef: UserGroupCrossRef)

    @Delete
    suspend fun delete(userGroupCrossRef: UserGroupCrossRef)

    @Query("SELECT * FROM usergroupcrossref u WHERE u.userId IS :userId")
    fun getByUserId(userId: Int): LiveData<UserGroupCrossRef>

}