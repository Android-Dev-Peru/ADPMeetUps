package domain.usecase

import domain.models.Event
import domain.repository.EventsRepository
import kotlinx.datetime.LocalDate

class GetEventInfo(private val eventsRepository: EventsRepository) {
    suspend fun invoke(date: LocalDate): Result<Event> {
        return eventsRepository.getEventInfo(date)
    }
}