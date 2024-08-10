package core

import data.repository.MockEventsRepository
import domain.EventsRepository

object RepositoryInjector {

    val eventsRepository: EventsRepository by lazy {
        MockEventsRepository(dispatcherProvider = CoreInjector.dispatcherProvider)
    }

}