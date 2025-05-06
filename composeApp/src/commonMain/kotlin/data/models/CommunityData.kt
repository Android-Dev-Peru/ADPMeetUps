package data.models

import domain.models.Community
import domain.models.Organizer
import domain.models.SocialMedia
import kotlinx.serialization.Serializable

@Serializable
data class CommunityEntity(
    val name: String,
    val topBanner: String,
    val description: String,
    val organizers: List<OrganizerEntity>,
    val exOrganizers: List<OrganizerEntity>,
    val socialMedia: List<SocialMediaEntity>
)

@Serializable
data class OrganizerEntity(
    val name: String,
    val photo: String,
    val url: String?,
)

@Serializable
data class SocialMediaEntity(
    val icon: String,
    val url: String
)

fun CommunityEntity.toDomain() = Community(
    name = this.name,
    topBanner = this.topBanner,
    description = this.description,
    organizers = this.organizers.map { it.toDomain() },
    exOrganizers = this.exOrganizers.map { it.toDomain() },
    socialMedia = this.socialMedia.map { it.toDomain() },
)

fun OrganizerEntity.toDomain() = Organizer(
    name = this.name,
    photo = this.photo,
    url = this.url,
)

fun SocialMediaEntity.toDomain() = SocialMedia(
    icon = this.icon,
    url = this.url
)
