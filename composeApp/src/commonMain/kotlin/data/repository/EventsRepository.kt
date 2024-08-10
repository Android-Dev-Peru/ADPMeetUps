package data.repository

import domain.Event
import domain.EventsRepository
import domain.IDispatcherProvider
import kotlinx.coroutines.withContext
import kotlinx.datetime.LocalDate

class EventsRepository(
    private val dispatcherProvider: IDispatcherProvider
) : EventsRepository {
    override suspend fun getEventInfo(date: LocalDate): Result<Event> {
        return withContext(dispatcherProvider.io()) {
            Result.failure(Exception("Feature Get Event not implemented yet"))
        }
    }

    override suspend fun getAllEvents(period: Int): Result<List<Event>> {
        return withContext(dispatcherProvider.io()) {
            Result.failure(Exception("Feature Get All Events not implemented yet"))
        }
    }

}