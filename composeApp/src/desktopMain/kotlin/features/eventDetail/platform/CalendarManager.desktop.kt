package features.eventDetail.platform

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

actual class CalendarManager actual constructor(private val context: Any?) {
    actual suspend fun addEventToCalendar(
        title: String,
        description: String,
        startTime: LocalTime,
        endTime: LocalTime,
        location: String,
        eventDate: LocalDate
    ): Boolean {
        TODO("Not yet implemented")
    }
}