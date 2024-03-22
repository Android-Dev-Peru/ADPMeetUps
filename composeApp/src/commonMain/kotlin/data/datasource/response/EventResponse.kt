package data.datasource.response

import domain.Event
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EventResponse(
    val status: String,
    val events: List<Event>
)