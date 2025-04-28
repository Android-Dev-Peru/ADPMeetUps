package features.eventDetail.platform

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import platform.EventKit.EKEntityType
import platform.EventKit.EKEvent
import platform.EventKit.EKEventStore
import platform.EventKit.EKSpan
import platform.Foundation.NSCalendar
import platform.Foundation.NSDate
import platform.Foundation.NSDateComponents
import platform.Foundation.NSError
import kotlin.coroutines.resume

actual class CalendarManager actual constructor(private val context: Any?) {

    private val eventStore = EKEventStore()

    @OptIn(ExperimentalForeignApi::class)
    actual suspend fun addEventToCalendar(
        title: String,
        description: String,
        startTime: LocalTime,
        endTime: LocalTime,
        location: String,
        eventDate: LocalDate
    ): Boolean = withContext(Dispatchers.Main){
        try {
            val permissionGranted = requestAccessSuspend()
            if (!permissionGranted) return@withContext false

            val startDateNS = createNSDate(eventDate, startTime)
            val endDateNS = createNSDate(eventDate, endTime)

            val event = EKEvent.eventWithEventStore(eventStore).apply {
                this.title = title
                this.notes = description
                this.startDate = startDateNS
                this.endDate = endDateNS
                this.location = location
                // Importante: asignar un calendario
                this.calendar = eventStore.defaultCalendarForNewEvents
            }

            //var errorPtr: NSError? = null
            val success = eventStore.saveEvent(event, EKSpan.EKSpanThisEvent, null)

            /*if (errorPtr != null) {
                println("Error al guardar evento: ${errorPtr?.localizedDescription}")
                return@withContext false
            }*/

            //eventStore.saveEvent(event, EKSpan.EKSpanThisEvent, null)
            success
        } catch (e: Exception) {
            println("Excepción al crear evento: ${e.message}")
            e.printStackTrace()
            false
        }
    }

    private fun createNSDate(date: LocalDate, time: LocalTime): NSDate {
        val calendar = NSCalendar.currentCalendar
        val components = NSDateComponents().apply {
            this.year = date.year.toLong()
            this.month = date.monthNumber.toLong()
            this.day = date.dayOfMonth.toLong()
            this.hour = time.hour.toLong()
            this.minute = time.minute.toLong()
            this.second = time.second.toLong()
        }
        return calendar.dateFromComponents(components) ?: NSDate()
    }

    private suspend fun requestAccessSuspend(): Boolean = suspendCancellableCoroutine { continuation ->
        eventStore.requestAccessToEntityType(
            EKEntityType.EKEntityTypeEvent
        ) { granted, error ->
            if (error != null) {
                println("Error al solicitar permisos: ${error.localizedDescription}")
            }
            continuation.resume(granted)
        }
    }

    private suspend fun requestAccess(): Boolean {
        return withContext(Dispatchers.Main) {
            var accessGranted = false
            eventStore.requestAccessToEntityType(
                EKEntityType.EKEntityTypeEvent
            ) { granted, error ->
                accessGranted = granted
            }
            accessGranted
        }
    }
}