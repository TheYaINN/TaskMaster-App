package de.taskmaster.model.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import de.taskmaster.model.data.impl.User

@Dao
interface UserDAO {

    @Insert
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("SELECT * FROM USR u WHERE u.userId IS :id")
    suspend fun getByID(id: Int): User?

    @Query("SELECT * FROM USR u WHERE u.userName IS :username")
    fun getByUserName(username: String): User?

}