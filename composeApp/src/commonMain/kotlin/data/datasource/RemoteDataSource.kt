package data.datasource

import data.datasource.response.EventResponse
import domain.Event
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class RemoteDataSource(private val httpClient: HttpClient) {

    suspend fun getEvents(): List<Event> {
        val response: EventResponse =
            httpClient.get("htttp;//...").body()
        return response.events

    }
}