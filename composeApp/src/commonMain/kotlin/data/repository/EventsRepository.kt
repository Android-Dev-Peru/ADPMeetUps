package data.repository

import domain.Event
import domain.EventsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.datetime.LocalDate

// TODO inject a dispatchers handler
class EventsRepository : EventsRepository {
    override suspend fun getEventInfo(date: LocalDate): Event {
        return withContext(Dispatchers.IO) {
            TODO("Not yet implemented")
        }
    }

    override suspend fun getAllEvent(period: Int): List<Event> {
        return withContext(Dispatchers.IO) {
            TODO("Not yet implemented")
        }
    }

}