package core

import domain.usecase.GetEventById
import domain.usecase.GetEventList

object DomainInjector {

    val getEventsList: GetEventList by lazy {
        GetEventList(eventsRepository = RepositoryInjector.eventsRepository)
    }

    val getEventDetail: GetEventById by lazy {
        GetEventById(eventsRepository = RepositoryInjector.eventsRepository)
    }

}