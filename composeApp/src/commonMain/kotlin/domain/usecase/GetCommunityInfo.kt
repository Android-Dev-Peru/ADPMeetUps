package domain.usecase

import domain.models.Community
import domain.repository.CommunityRepository

class GetCommunityInfo(private val communityRepository: CommunityRepository) {
    suspend fun invoke(): Result<Community> {
        return communityRepository.getCommunityInfo()
    }
}
