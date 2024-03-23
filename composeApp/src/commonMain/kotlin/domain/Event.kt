package domain

import kotlinx.datetime.LocalDate

/**
 * A MeetUp Event.
 */
data class Event(
    val id: String,
    val title: String,
    val description: String,
    val createdAt: LocalDate,
    val lastUpdate: LocalDate,
    val eventBannerUrl: String,
    val speakers: Speaker,
    val evenType: EventType,
    val eventLocation: EventLocation,
    val addressInfo: String
)

data class Speaker(
    val name: String,
    val profilePicUrl: String?
)

enum class EventType { MEETUP, WORKSHOP }

enum class EventLocation { ONLINE, IN_PERSON }