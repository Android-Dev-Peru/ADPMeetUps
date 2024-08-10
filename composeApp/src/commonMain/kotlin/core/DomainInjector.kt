package core

import domain.usecase.GetEventList

object DomainInjector {

    val getEventsList: GetEventList by lazy {
        GetEventList(eventsRepository = RepositoryInjector.eventsRepository)
    }

}