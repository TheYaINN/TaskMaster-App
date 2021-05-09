package de.taskmaster.db

import android.app.Application
import de.taskmaster.model.data.GroupDAO
import de.taskmaster.model.data.ListDAO
import de.taskmaster.model.data.TodoListWithAssociationsDAO
import de.taskmaster.model.data.UserDAO
import de.taskmaster.model.data.UserWithAssociationsDAO
import de.taskmaster.model.data.impl.ToDoList
import de.taskmaster.model.data.impl.User
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LocalDataBaseConnector {

    lateinit var userDAO: UserDAO
    lateinit var userWithAssociationsDAO: UserWithAssociationsDAO
    lateinit var todoListWithAssociationsDAO: TodoListWithAssociationsDAO
    lateinit var toDoListDAO: ListDAO
    lateinit var groupDAO: GroupDAO

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
    }

    fun insert(user: User) {
        GlobalScope.launch {
            userDAO.insert(user)
        }
    }

    fun delete(taskList: ToDoList) {
        GlobalScope.launch {
            toDoListDAO.delete(taskList)
        }
    }
}