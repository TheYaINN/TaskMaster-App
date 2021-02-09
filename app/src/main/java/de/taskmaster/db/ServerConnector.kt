package de.taskmaster.db

import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.BufferedReader
import java.net.URL

fun main() {
    val response = ServerConnector.INSTANCE.getRequest(
        "/login/register/checkForExisting",
        mapOf(Pair("username", "test"), Pair("mail", "test"))
    )

    print(response)
}

class ServerConnector : APIConnector {

    private val server = "http://localhost:8085"

    override fun postRequest(
        specifiedPath: String,
        body: String,
        parameters: Map<String, String>,
    ): String {
        val client = OkHttpClient()

        val url = buildUrl(specifiedPath, parameters)
        val requestBody = body.toRequestBody("application/json".toMediaType())
        val request = Request.Builder().url(url).post(requestBody).build()

        val response = client.newCall(request).execute()
        return BufferedReader(response.body?.charStream()).readLine()
    }

    //specifiedPath starts with /
    //parameter.key is the parametername and parameters.value is the parameter value
    override fun getRequest(specifiedPath: String, parameters: Map<String, String>): String {
        val client = OkHttpClient()

        val url = buildUrl(specifiedPath, parameters)
        val request = Request.Builder().url(url).build()

        val response = client.newCall(request).execute()
        return BufferedReader(response.body?.charStream()).readLine()
    }

    private fun buildUrl(specifiedPath: String, parameters: Map<String, String>): String {
        val builder = URL(server + specifiedPath).toHttpUrlOrNull()?.newBuilder() ?: error("error")
        parameters.forEach { entry -> builder.addQueryParameter(entry.key, entry.value) }
        return builder.build().toString()
    }

    companion object {
        val INSTANCE: APIConnector = ServerConnector()
    }
}
