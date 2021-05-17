package de.taskmaster.model.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import de.taskmaster.model.data.impl.UserWithAssociations

@Dao
interface UserWithAssociationsDAO {

    @Query("SELECT * FROM usr u WHERE u.userId == :id")
    fun getByUserId(id: Int): LiveData<UserWithAssociations>

}