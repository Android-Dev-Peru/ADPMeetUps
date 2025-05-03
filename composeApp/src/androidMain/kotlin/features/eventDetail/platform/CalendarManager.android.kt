package features.eventDetail.platform

import android.Manifest
import android.app.Activity
import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.content.pm.PackageManager
import android.provider.CalendarContract
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import java.util.Calendar
import java.util.TimeZone

actual class CalendarManager actual constructor(private val context: Any?) {
    actual suspend fun addEventToCalendar(
        title: String,
        description: String,
        startTime: LocalTime,
        endTime: LocalTime,
        location: String,
        eventDate: LocalDate
    ): Boolean = withContext(Dispatchers.IO) {
        val context = context as? Context

        if (!hasCalendarPermissions(context!!)) {
            requestCalendarPermissions(context)
            return@withContext false
        }

        try {
            val timeZone = TimeZone.getTimeZone("GMT-5")
            val calendar = Calendar.getInstance(timeZone)

            calendar.set(eventDate.year, eventDate.monthNumber - 1, eventDate.dayOfMonth)

            calendar.set(Calendar.HOUR_OF_DAY, startTime.hour)
            calendar.set(Calendar.MINUTE, startTime.minute)
            calendar.set(Calendar.SECOND, startTime.second)

            val eventStartTime = calendar.timeInMillis

            calendar.set(Calendar.HOUR_OF_DAY, endTime.hour)
            calendar.set(Calendar.MINUTE, endTime.minute)
            calendar.set(Calendar.SECOND, endTime.second)

            val eventEndTime = calendar.timeInMillis

            val contentResolver: ContentResolver = context.contentResolver
            val values = ContentValues().apply {
                put(CalendarContract.Events.TITLE, title)
                put(CalendarContract.Events.DESCRIPTION, description)
                put(CalendarContract.Events.DTSTART, eventStartTime)
                put(CalendarContract.Events.DTEND, eventEndTime)
                put(CalendarContract.Events.EVENT_LOCATION, location)
                put(CalendarContract.Events.CALENDAR_ID, getDefaultCalendarId(context))
                put(CalendarContract.Events.EVENT_TIMEZONE, "GMT-5")
            }

            contentResolver.insert(CalendarContract.Events.CONTENT_URI, values)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    private fun hasCalendarPermissions(context: Context): Boolean {
        return (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CALENDAR) ==
                PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_CALENDAR) ==
                PackageManager.PERMISSION_GRANTED)
    }

    private fun requestCalendarPermissions(context: Context) {
        if (context is Activity) {
            ActivityCompat.requestPermissions(
                context,
                arrayOf(
                    Manifest.permission.READ_CALENDAR,
                    Manifest.permission.WRITE_CALENDAR
                ),
                CALENDAR_PERMISSION_REQUEST_CODE
            )
        }
    }

    private fun getDefaultCalendarId(context: Context): Long {
        val projection = arrayOf(
            CalendarContract.Calendars._ID,
            CalendarContract.Calendars.CALENDAR_DISPLAY_NAME
        )

        val uri = CalendarContract.Calendars.CONTENT_URI
        val selection = "(${CalendarContract.Calendars.VISIBLE} = 1)"

        context.contentResolver.query(uri, projection, selection, null, null)?.use { cursor ->
            if (cursor.moveToFirst()) {
                val calendarId = cursor.getLong(0)
                return calendarId
            }
        }

        return 1
    }

    companion object {
        private const val CALENDAR_PERMISSION_REQUEST_CODE = 100
    }
}