package de.taskmaster

import java.io.*
import java.net.URL

class ServerConnector : APIConnector {

    private val server = "217.160.242.136:3308"

    override fun postRequest(vararg arguments: String): String {
        val url = URL(server + arguments.first())
        val connection = url.openConnection()
        val out = BufferedOutputStream(connection.getOutputStream())

        val writer = BufferedWriter(OutputStreamWriter(out, "UTF-8"))
        arguments.drop(1).forEach { writer.write(it) }
        writer.flush()
        writer.close()
        out.close()
        val input = BufferedInputStream(connection.getInputStream())

        val response = BufferedReader(InputStreamReader(input, "UTF-8"))
        response.readLine()

        connection.connect()
        response.close()

        return response.readText()
    }


    companion object {
        val INSTANCE: APIConnector = ServerConnector()
    }
}
