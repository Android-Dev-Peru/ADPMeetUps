package domain.usecase

import domain.Event
import domain.EventsRepository

class GetEventList(private val eventsRepository: EventsRepository) {
    suspend fun invoke(period: Int): Result<List<Event>> {
        return eventsRepository.getAllEvents(period)
    }
}