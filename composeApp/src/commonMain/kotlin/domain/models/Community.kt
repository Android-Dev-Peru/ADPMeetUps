package domain.models

data class Community(
    val name: String,
    val topBanner: String,
    val description: String,
    val organizers: List<Organizer>,
    val exOrganizers: List<Organizer>,
    val socialMedia: List<SocialMedia>
)

data class Organizer(
    val name: String,
    val photo: String,
    val url: String?,
)

data class SocialMedia(
    val icon: String,
    val url: String
)
