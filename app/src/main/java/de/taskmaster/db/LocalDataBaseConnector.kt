package de.taskmaster.db

import android.app.Application
import de.taskmaster.model.data.GroupDAO
import de.taskmaster.model.data.ListDAO
import de.taskmaster.model.data.TaskDAO
import de.taskmaster.model.data.TodoListWithAssociationsDAO
import de.taskmaster.model.data.UserDAO
import de.taskmaster.model.data.UserWithAssociationsDAO

class LocalDataBaseConnector {

    lateinit var userDAO: UserDAO
    lateinit var userWithAssociationsDAO: UserWithAssociationsDAO
    lateinit var toDoListDAO: ListDAO
    lateinit var todoListWithAssociationsDAO: TodoListWithAssociationsDAO
    lateinit var groupDAO: GroupDAO
    lateinit var taskDAO: TaskDAO

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
    }
}