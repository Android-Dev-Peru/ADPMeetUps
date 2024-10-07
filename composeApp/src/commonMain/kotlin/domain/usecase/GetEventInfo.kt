package domain.usecase

import domain.Event
import domain.EventsRepository
import kotlinx.datetime.LocalDate

class GetEventInfo(private val eventsRepository: EventsRepository) {
    suspend fun invoke(date: LocalDate): Result<Event> {
        return eventsRepository.getEventInfo(date)
    }
}