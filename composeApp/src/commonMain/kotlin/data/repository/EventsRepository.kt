package data.repository

import data.datasource.Failure
import data.datasource.LocalDataSource
import data.datasource.RemoteDataSource
import data.datasource.ResultType
import domain.Event
import domain.EventLocation
import domain.EventType
import domain.Speaker
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate

class EventsRepository(
    private val dataSource: LocalDataSource,
    private val service: RemoteDataSource
) {
    suspend fun getAllEvent(period: Int): ResultType<List<Event>, Failure> {

        // TODO FALTA DEFINIR,COMPORTAMIENTO LOCAL Y REMOTO
        return ResultType.Success(emptyList())
    }

    suspend fun getEvent(date: LocalDate): ResultType<Event, Failure> {

        // TODO FALTA DEFINIR,COMPORTAMIENTO LOCAL Y REMOTO
        val speaker = Speaker("zxx")
        val event = Event(
            "s", "s", "s", date, date, "",
            speaker, EventType.METTUP, EventLocation.ONLINE, ""
        )

        return ResultType.Success(event)
    }
}