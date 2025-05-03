package data.repository

import domain.models.Event
import domain.models.EventLocation
import domain.models.EventTalk
import domain.repository.EventsRepository
import domain.IDispatcherProvider
import domain.models.EventType
import domain.models.Speaker
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

class MockEventsRepository(
    private val dispatcherProvider: IDispatcherProvider
) : EventsRepository {

    private val _events = MutableStateFlow<List<Event>>(emptyList())
    private val events = _events.asStateFlow()

    override suspend fun getEventInfo(date: LocalDate): Result<Event> {
        return withContext(dispatcherProvider.io()) {
            Result.failure(Exception("Feature Get Event not implemented yet"))
        }
    }

    override suspend fun getAllEvents(period: Int): Result<List<Event>> {
        return withContext(dispatcherProvider.io()) {
            _events.value = listOf(
                Event(
                    id = "1",
                    title = "Android Dev Perú Conf",
                    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.",
                    date = LocalDate(2025, 4, 29),
                    startTime = LocalTime(19, 30),
                    endTime = LocalTime(22, 0),
                    createdAt = LocalDate(2024, 1, 1),
                    lastUpdate = LocalDate(2024, 10, 9),
                    eventBannerUrl = "https://dev-to-uploads.s3.amazonaws.com/uploads/articles/x34agc51zzdtnt6x8jbw.jpeg",
                    talks = listOf(
                        EventTalk(
                            title = "Charla 1",
                            description = "Charla numero 1",
                            speakers = listOf(
                                Speaker(
                                    name = "Jose",
                                    profilePicUrl = "https://media2.dev.to/dynamic/image/width=90,height=90,fit=cover,gravity=auto,format=auto/https%3A%2F%2Fdev-to-uploads.s3.amazonaws.com%2Fuploads%2Fuser%2Fprofile_image%2F1108161%2F2d8e5319-3b77-4789-b565-e5e9149d0c42.png"
                                ),
                                Speaker(
                                    name = "Flavio",
                                    profilePicUrl = "https://media2.dev.to/dynamic/image/width=90,height=90,fit=cover,gravity=auto,format=auto/https%3A%2F%2Fdev-to-uploads.s3.amazonaws.com%2Fuploads%2Fuser%2Fprofile_image%2F1108161%2F2d8e5319-3b77-4789-b565-e5e9149d0c42.png"
                                )
                            ),
                            bannerUrl = "https://res.cloudinary.com/practicaldev/image/fetch/s--AL-Z6Dmp--/c_limit%2Cf_auto%2Cfl_progressive%2Cq_auto%2Cw_800/https://dev-to-uploads.s3.amazonaws.com/uploads/articles/xd3pk3kbay51di61bkiv.jpeg"
                        ),
                        EventTalk(
                            title = "Charla 2",
                            description = "Charla numero 2",
                            speakers = listOf(
                                Speaker(
                                    name = "Jose",
                                    profilePicUrl = "https://media2.dev.to/dynamic/image/width=90,height=90,fit=cover,gravity=auto,format=auto/https%3A%2F%2Fdev-to-uploads.s3.amazonaws.com%2Fuploads%2Fuser%2Fprofile_image%2F1108161%2F2d8e5319-3b77-4789-b565-e5e9149d0c42.png"
                                ),
                                Speaker(
                                    name = "Flavio",
                                    profilePicUrl = "https://media2.dev.to/dynamic/image/width=90,height=90,fit=cover,gravity=auto,format=auto/https%3A%2F%2Fdev-to-uploads.s3.amazonaws.com%2Fuploads%2Fuser%2Fprofile_image%2F1108161%2F2d8e5319-3b77-4789-b565-e5e9149d0c42.png"
                                )
                            ),
                            bannerUrl = "https://res.cloudinary.com/practicaldev/image/fetch/s--AL-Z6Dmp--/c_limit%2Cf_auto%2Cfl_progressive%2Cq_auto%2Cw_800/https://dev-to-uploads.s3.amazonaws.com/uploads/articles/xd3pk3kbay51di61bkiv.jpeg"
                        )
                    ),
                    evenType = EventType.MEETUP,
                    eventLocation = EventLocation.IN_PERSON,
                    addressInfo = "UPC Monterrico - Prolongación Primavera 2390, Santiago de Surco, Lima, Perú"
                ),
                Event(
                    id = "2",
                    title = "Android Dev Perú Conf 2",
                    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.",
                    date = LocalDate(2024, 10, 19),
                    startTime = LocalTime(19, 30),
                    endTime = LocalTime(22,0),
                    createdAt = LocalDate(2024, 1, 1),
                    lastUpdate = LocalDate(2024, 10, 9),
                    eventBannerUrl = "https://media2.dev.to/dynamic/image/width=800%2Cheight=%2Cfit=scale-down%2Cgravity=auto%2Cformat=auto/https%3A%2F%2Fdev-to-uploads.s3.amazonaws.com%2Fuploads%2Farticles%2Fzc07gtqjwf39r5qczukn.jpeg",
                    talks = listOf(
                        EventTalk(
                            title = "Charla 1",
                            description = "Charla numero 1",
                            speakers = listOf(
                                Speaker(
                                    name = "Jose",
                                    profilePicUrl = "https://media2.dev.to/dynamic/image/width=90,height=90,fit=cover,gravity=auto,format=auto/https%3A%2F%2Fdev-to-uploads.s3.amazonaws.com%2Fuploads%2Fuser%2Fprofile_image%2F1108161%2F2d8e5319-3b77-4789-b565-e5e9149d0c42.png"
                                )
                            ),
                            bannerUrl = "https://res.cloudinary.com/practicaldev/image/fetch/s--AL-Z6Dmp--/c_limit%2Cf_auto%2Cfl_progressive%2Cq_auto%2Cw_800/https://dev-to-uploads.s3.amazonaws.com/uploads/articles/xd3pk3kbay51di61bkiv.jpeg"
                        )
                    ),
                    evenType = EventType.MEETUP,
                    eventLocation = EventLocation.IN_PERSON,
                    addressInfo = "NttData"
                )
            )
            Result.success(events.value)
        }
    }

    override suspend fun getEventById(id: String): Result<Event?> {
        return withContext(dispatcherProvider.io()) {
            Result.success(
                events.value.find {  it.id == id }
            )
        }
    }

}