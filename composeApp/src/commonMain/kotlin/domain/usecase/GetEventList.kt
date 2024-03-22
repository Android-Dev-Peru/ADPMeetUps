package domain.usecase

import data.datasource.Failure
import data.datasource.ResultType
import data.repository.EventsRepository
import domain.Event
import kotlinx.datetime.DatePeriod

class GetEventList(private val eventsRepository: EventsRepository) {
    suspend fun invoke(period: Int): ResultType<List<Event>, Failure> =
        eventsRepository.getAllEvent(period)
}