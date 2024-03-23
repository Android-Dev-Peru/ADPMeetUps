package domain

import kotlinx.datetime.LocalDate

interface EventsRepository {
    suspend fun getEventInfo(date: LocalDate): Event
    suspend fun getAllEvent(period: Int): List<Event>
}