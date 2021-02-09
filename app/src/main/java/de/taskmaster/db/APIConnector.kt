package de.taskmaster.db

interface APIConnector {

    fun postRequest(specifiedPath: String, body: String, parameters: Map<String, String>): String

    fun getRequest(specifiedPath: String, parameters: Map<String, String>): String

}