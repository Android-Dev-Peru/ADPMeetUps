package data.repository

import domain.Event
import domain.EventLocation
import domain.EventTalk
import domain.EventsRepository
import domain.IDispatcherProvider
import domain.Speaker
import kotlinx.coroutines.withContext
import kotlinx.datetime.LocalDate

class MockEventsRepository(
    private val dispatcherProvider: IDispatcherProvider
) : EventsRepository {
    override suspend fun getEventInfo(date: LocalDate): Result<Event> {
        return withContext(dispatcherProvider.io()) {
            Result.failure(Exception("Feature Get Event not implemented yet"))
        }
    }

    override suspend fun getAllEvents(period: Int): Result<List<Event>> {
        return withContext(dispatcherProvider.io()) {
            val events = listOf(
                Event(
                    id = "1",
                    title = "Android Dev Perú Conf",
                    description = "Android Dev Perú Conf",
                    createdAt = LocalDate(2024, 1, 1),
                    lastUpdate = LocalDate(2024, 10, 9),
                    eventBannerUrl = "",
                    talks = listOf(
                        EventTalk(
                            title = "Charla 1",
                            description = "Charla numero 1",
                            speakers = listOf(
                                Speaker(
                                    name = "Jose",
                                    profilePicUrl = null
                                )
                            ),
                            bannerUrl = ""
                        )
                    ),
                    evenType = domain.EventType.MEETUP,
                    eventLocation = EventLocation.IN_PERSON,
                    addressInfo = ""
                )
            )
            Result.success(events)
        }
    }

}