package de.taskmaster.db

interface APIConnector {

    fun postRequest(vararg arguments: String): String

}