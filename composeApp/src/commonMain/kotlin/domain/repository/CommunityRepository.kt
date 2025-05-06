package domain.repository

import domain.models.Community

interface CommunityRepository {

    suspend fun getCommunityInfo(): Result<Community>
}