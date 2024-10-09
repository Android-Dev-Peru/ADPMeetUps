package core.ui

sealed class AdpDestination(val route: String) {

    data object Home : AdpDestination("home")

    data object LiveEvent : AdpDestination("liveEvent")

    data object CommunityDetails : AdpDestination("communityDetails")

    data object EventDetail : AdpDestination("detail/{eventId}")
}