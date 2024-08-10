package domain

import kotlinx.datetime.LocalDate

interface EventsRepository {
    suspend fun getEventInfo(date: LocalDate): Result<Event>
    suspend fun getAllEvents(period: Int): Result<List<Event>>
}