package domain.usecase

import data.repository.EventsRepository
import domain.Event

class GetEventList(private val eventsRepository: EventsRepository) {
    suspend fun invoke(period: Int): Result<List<Event>> {
        return eventsRepository.getAllEvents(period)
    }
}