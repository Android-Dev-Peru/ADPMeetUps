package core

import domain.usecase.GetCommunityInfo
import domain.usecase.GetEventById
import domain.usecase.GetEventList

object DomainInjector {

    val getEventsList: GetEventList by lazy {
        GetEventList(eventsRepository = RepositoryInjector.eventsRepository)
    }

    val getEventDetail: GetEventById by lazy {
        GetEventById(eventsRepository = RepositoryInjector.eventsRepository)
    }

    val getCommunityInfo: GetCommunityInfo by lazy {
        GetCommunityInfo(communityRepository = RepositoryInjector.communityRepository)
    }
}
