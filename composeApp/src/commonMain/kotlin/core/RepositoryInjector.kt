package core

import data.repository.MockCommunityRepository
import data.repository.MockEventsRepository
import domain.repository.CommunityRepository
import domain.repository.EventsRepository

object RepositoryInjector {

    val eventsRepository: EventsRepository by lazy {
        MockEventsRepository(dispatcherProvider = CoreInjector.dispatcherProvider)
    }

    val communityRepository: CommunityRepository by lazy {
        MockCommunityRepository(dispatcherProvider = CoreInjector.dispatcherProvider)
    }

}