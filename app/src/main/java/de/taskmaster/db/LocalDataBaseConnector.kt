package de.taskmaster.db

import android.app.Application
import de.taskmaster.model.data.*

class LocalDataBaseConnector {

    lateinit var userDAO: UserDAO
    lateinit var userWithAssociationsDAO: UserWithAssociationsDAO
    lateinit var toDoListDAO: ToDoListDAO
    lateinit var todoListWithAssociationsDAO: TodoListWithAssociationsDAO
    lateinit var groupDAO: GroupDAO
    lateinit var taskDAO: TaskDAO
    lateinit var addressDAO: AddressDAO
    lateinit var groupWithTodDoListDao: GroupWithToDoListsDAO

    companion object {
        val instance by lazy { LocalDataBaseConnector() }
    }

    fun init(application: Application) {
        val db = AppDataBase.getInstance(application) ?: error("Could not instantiate DataBase")
        userDAO = db.userDAO()
        userWithAssociationsDAO = db.userWithAssociationsDAO()
        todoListWithAssociationsDAO = db.todoListWithAssociationsDAO()
        toDoListDAO = db.listDAO()
        groupDAO = db.groupDAO()
        taskDAO = db.taskDAO()
        addressDAO = db.addressDAO()
        groupWithTodDoListDao = db.groupWithToDoListDAO()
    }
}