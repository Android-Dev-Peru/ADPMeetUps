package domain.usecase

import domain.Event
import domain.EventsRepository

class GetEventById(private val eventsRepository: EventsRepository) {
    suspend fun invoke(id: String): Result<Event?> {
        return eventsRepository.getEventById(id)
    }
}