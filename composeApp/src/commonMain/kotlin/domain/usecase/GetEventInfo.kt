package domain.usecase

import data.datasource.Failure
import data.datasource.ResultType
import data.repository.EventsRepository
import domain.Event
import kotlinx.datetime.LocalDate

class GetEventInfo(private val eventsRepository: EventsRepository) {
    suspend fun invoke(date: LocalDate): ResultType<Event, Failure> {
        return ResultType.Success(eventsRepository.getEventInfo(date))
    }
}