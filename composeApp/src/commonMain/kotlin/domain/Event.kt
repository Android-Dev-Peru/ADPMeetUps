package domain

data class Event (
    val id: String,
    val title: String,
    val description: String,
    val createdAt: String,
    val lastUpdate: String,
    val eventBannerUrl : String,
    val speakers: Speaker,
    val evenType: EventType,
    val eventLocation:EventLocation,
    val addressInfo:String
)
enum class EventType{METTUP,WORKSHOP}
enum class EventLocation{ONLINE,INPERSON}
data class Speaker(
    val name:String,
)