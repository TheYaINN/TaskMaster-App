package de.taskmaster.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import de.taskmaster.model.data.GroupDAO
import de.taskmaster.model.data.ListDAO
import de.taskmaster.model.data.TodoListWithAssociationsDAO
import de.taskmaster.model.data.UserDAO
import de.taskmaster.model.data.UserWithAssociationsDAO
import de.taskmaster.model.data.impl.Address
import de.taskmaster.model.data.impl.Group
import de.taskmaster.model.data.impl.Tag
import de.taskmaster.model.data.impl.Task
import de.taskmaster.model.data.impl.ToDoList
import de.taskmaster.model.data.impl.User

@Database(
    exportSchema = false,
    entities = [User::class, Group::class, Tag::class, ToDoList::class, Task::class, Address::class],
    version = 4
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
                    .build()
            }
            return instance
        }
    }

    abstract fun userDAO(): UserDAO
    abstract fun userWithAssociationsDAO(): UserWithAssociationsDAO
    abstract fun todoListWithAssociationsDAO(): TodoListWithAssociationsDAO
    abstract fun listDAO(): ListDAO
    abstract fun groupDAO(): GroupDAO
}