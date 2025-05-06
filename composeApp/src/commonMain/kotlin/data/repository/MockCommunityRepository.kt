package data.repository

import domain.IDispatcherProvider
import domain.models.Community
import domain.models.Organizer
import domain.models.SocialMedia
import domain.repository.CommunityRepository
import kotlinx.coroutines.withContext

class MockCommunityRepository(
    private val dispatcherProvider: IDispatcherProvider
): CommunityRepository {

    override suspend fun getCommunityInfo(): Result<Community> {
        return withContext(dispatcherProvider.io()) {
            val community = Community(
                name = "Android Dev Peru",
                topBanner = "https://res.cloudinary.com/practicaldev/image/fetch/s--AL-Z6Dmp--/c_limit%2Cf_auto%2Cfl_progressive%2Cq_auto%2Cw_800/https://dev-to-uploads.s3.amazonaws.com/uploads/articles/xd3pk3kbay51di61bkiv.jpeg",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.",
                organizers = listOf(
                    Organizer(
                        name = "Jose Flavio",
                        photo = "https://media2.dev.to/dynamic/image/width=90,height=90,fit=cover,gravity=auto,format=auto/https%3A%2F%2Fdev-to-uploads.s3.amazonaws.com%2Fuploads%2Fuser%2Fprofile_image%2F1108161%2F2d8e5319-3b77-4789-b565-e5e9149d0c42.png",
                        url = "https://www.linkedin.com/in/jflavio11/",
                    ),
                ),
                exOrganizers = listOf(
                    Organizer(
                        name = "Ex Organizer",
                        photo = "https://media2.dev.to/dynamic/image/width=90,height=90,fit=cover,gravity=auto,format=auto/https%3A%2F%2Fdev-to-uploads.s3.amazonaws.com%2Fuploads%2Fuser%2Fprofile_image%2F1108161%2F2d8e5319-3b77-4789-b565-e5e9149d0c42.png",
                        url = "https://www.linkedin.com/in/jflavio11/",
                    ),
                ),
                socialMedia = listOf(
                    SocialMedia(
                        icon = "https://cdn.simpleicons.org/whatsapp",
                        url = "https://chat.whatsapp.com/Il7yhDYCj8zLVDrK7OpFOm"
                    ),
                    SocialMedia(
                        icon = "https://cdn.simpleicons.org/youtube",
                        url = "https://www.youtube.com/@AndroidDevPeru"
                    ),
                    SocialMedia(
                        icon = "https://cdn.simpleicons.org/x",
                        url = "https://twitter.com/androiddevperu"
                    ),
                    SocialMedia(
                        icon = "https://cdn.simpleicons.org/instagram",
                        url = "https://www.instagram.com/androiddevperu/"
                    ),
                    SocialMedia(
                        icon = "https://cdn.simpleicons.org/linkedin",
                        url = "https://www.linkedin.com/company/android-dev-peru"
                    ),
                    SocialMedia(
                        icon = "https://cdn.simpleicons.org/github",
                        url = "https://github.com/Android-Dev-Peru"
                    ),
                    SocialMedia(
                        icon = "https://cdn.simpleicons.org/tiktok",
                        url = "https://www.tiktok.com/@androiddevperu"
                    )
                )
            )
            Result.success(community)
        }
    }
}