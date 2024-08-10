package domain.usecase

import data.repository.EventsRepository
import domain.Event
import kotlinx.datetime.LocalDate

class GetEventInfo(private val eventsRepository: EventsRepository) {
    suspend fun invoke(date: LocalDate): Result<Event> {
        return eventsRepository.getEventInfo(date)
    }
}