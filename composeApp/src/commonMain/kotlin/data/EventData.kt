package data

import domain.Event
import domain.EventLocation
import domain.EventTalk
import domain.EventType
import domain.Speaker
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class EventEntity(
    val id: String,
    val title: String,
    val description: String,
    val createdAt: LocalDate,
    val lastUpdate: LocalDate,
    val eventBannerUrl: String,
    val talks: List<EventTalkEntity>,
    val evenType: String,
    val eventLocation: String,
    val addressInfo: String
)

@Serializable
data class EventTalkEntity(
    val title: String,
    val description: String,
    val speakers: List<SpeakerEntity>,
    val bannerUrl: String? = null
)

@Serializable
data class SpeakerEntity(
    val name: String, val profilePicUrl: String?
)

fun EventEntity.toDomain() = Event(
    id = this.id,
    title = this.title,
    description = this.description,
    createdAt = this.createdAt,
    lastUpdate = this.lastUpdate,
    eventBannerUrl = this.eventBannerUrl,
    talks = this.talks.map { it.toDomain() },
    evenType = EventType.valueOf(this.evenType),
    eventLocation = EventLocation.valueOf(this.eventLocation),
    addressInfo = this.addressInfo
)

fun EventTalkEntity.toDomain() = EventTalk(
    title = this.title,
    description = this.description,
    speakers = this.speakers.map { it.toDomain() },
    bannerUrl = this.bannerUrl
)

fun SpeakerEntity.toDomain() = Speaker(
    name = this.name,
    profilePicUrl = this.profilePicUrl
)