package br.com.domain.services.user

import br.com.application.payloads.mappers.toUserResponse
import br.com.application.payloads.response.UserResponse
import br.com.infra.repository.user.UserReadOnlyRepository

class GetUserProfileService(
    private val userReadOnlyRepository: UserReadOnlyRepository
) {

    suspend fun getProfileUserById(userId: String): UserResponse {
        val user = userReadOnlyRepository.findById(userId)
        return user.toUserResponse()
    }

}