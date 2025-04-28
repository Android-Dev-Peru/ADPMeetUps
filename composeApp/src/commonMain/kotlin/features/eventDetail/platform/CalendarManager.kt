package features.eventDetail.platform

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

expect class CalendarManager(context: Any?) {
    suspend fun addEventToCalendar(
        title: String,
        description: String,
        startTime: LocalTime,
        endTime: LocalTime,
        location: String = "",
        eventDate: LocalDate
    ): Boolean
}