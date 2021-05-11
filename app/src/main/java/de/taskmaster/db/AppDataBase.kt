package de.taskmaster.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import de.taskmaster.model.data.*
import de.taskmaster.model.data.impl.*

@Database(
    exportSchema = false,
    entities = [User::class, Group::class, Tag::class, ToDoList::class, Task::class, Address::class, UserGroupCrossRef::class],
    version = 8
)
@TypeConverters(DBConverter::class)
abstract class AppDataBase : RoomDatabase() {

    companion object {
        private var instance: AppDataBase? = null

        @Synchronized
        fun getInstance(context: Context): AppDataBase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "appDataBase"
                )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance
        }
    }

    abstract fun userDAO(): UserDAO
    abstract fun userWithAssociationsDAO(): UserWithAssociationsDAO
    abstract fun todoListWithAssociationsDAO(): TodoListWithAssociationsDAO
    abstract fun listDAO(): ToDoListDAO
    abstract fun groupDAO(): GroupDAO
    abstract fun taskDAO(): TaskDAO
    abstract fun addressDAO(): AddressDAO
    abstract fun groupWithToDoListDAO(): GroupWithToDoListsDAO
}