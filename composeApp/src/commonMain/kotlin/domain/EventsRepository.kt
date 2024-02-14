package domain

import kotlinx.datetime.LocalDate

interface EventsRepository {
    suspend fun getEventInfo(date: LocalDate)
    suspend fun getAllEvent(period: Int)
}