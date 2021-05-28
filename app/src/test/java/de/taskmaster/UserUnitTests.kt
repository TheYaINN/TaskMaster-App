package de.taskmaster

import de.taskmaster.auth.SecurityHelper
import de.taskmaster.model.data.UserDAO
import de.taskmaster.model.data.impl.User
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import org.junit.After
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

/**
 * Ziel dieser Klasse ist es zufällig gewählte Funktionen der Datenbank zu testen mit Mockobjekten.
 * Bei der "echten" Anwendung wird dieser Code nicht vom Programmierer geschrieben sondern die Kommunikation wird von einem Framework übernommen.
 */

class UserUnitTests {


    private lateinit var user: User
    private lateinit var userImpl: UserImpl

    private val defaultUsername = "TheYaINN"


    @Before
    fun init() {
        val pass = "securepass"
        val split = SecurityHelper.generateHashedPassword(pass).split(":")
        user = User(
            username = defaultUsername,
            password = split[2],
            salt = split[1],
            iterations = split[0].toInt(),
            firstName = "Bengt",
            lastName = "Joachimsohn",
            email = "bengt@joachimsohn.de"
        )
        userImpl = UserImpl()
    }

    @Test
    fun create() {
        GlobalScope.async {
            userImpl.insert(user)
            assert(userImpl.getByUserName(defaultUsername) == user)
        }
    }

    @After
    fun clear() {
        userImpl.clear()
    }

    @Test
    fun update() {
        val alias = "Friedrich"
        val modifiedUser = user.apply { username = alias }

        GlobalScope.async {
            userImpl.insert(user)
            userImpl.update(modifiedUser)
            assert(userImpl.getByUserName(alias) == user)
        }
    }


}

/**
 * Mock des Interfaces für die Benutzerverwaltung
 */
class UserImpl : UserDAO {

    private var users: MutableList<User> = mutableListOf()

    override suspend fun insert(user: User) {
        println("Inserting user")
        users.add(user)
    }

    override suspend fun update(user: User) {
        println("Updating user")
        users.first { it == user }.apply {
            username = user.username
            email = user.email
            firstName = user.firstName
            lastName = user.lastName
            password = user.password
            salt = user.salt
            iterations = user.iterations
            profilePicture = user.profilePicture
        }
    }

    override suspend fun delete(user: User) {
        println("Deleting user")
        users.remove(user)
    }

    override suspend fun getByID(id: Int): User? {
        println("Getting user by ID")
        return users.find { it.userId == id }
    }

    override fun getByUserName(username: String): User? {
        println("Getting user by UserName")
        return users.find { it.username == username }
    }

    fun clear() {
        println("Clearing list of users")
        users.clear()
    }

}