package de.taskmaster.db

import android.app.Application
import de.taskmaster.model.data.GroupDAO
import de.taskmaster.model.data.ListDAO
import de.taskmaster.model.data.UserDAO
import de.taskmaster.model.data.UserWithAssociationsDAO

//TODO refactor
class LocalDataBaseConnector {

    lateinit var userDAO: UserDAO
    lateinit var userWithAssociationsDAO: UserWithAssociationsDAO
    lateinit var listDAO: ListDAO
    lateinit var groupDAO: GroupDAO

    companion object {
        val instance by lazy { LocalDataBaseConnector() }
    }

    fun init(application: Application) {
        val db = AppDataBase.getInstance(application) ?: error("Could not instantiate DataBase")
        userDAO = db.userDAO()
        userWithAssociationsDAO = db.userWithAssociationsDAO()
        listDAO = db.listDAO()
        groupDAO = db.groupDAO()
    }

}