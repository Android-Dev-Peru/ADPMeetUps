package domain.usecase

import domain.models.Event
import domain.repository.EventsRepository

class GetEventById(private val eventsRepository: EventsRepository) {
    suspend fun invoke(id: String): Result<Event?> {
        return eventsRepository.getEventById(id)
    }
}