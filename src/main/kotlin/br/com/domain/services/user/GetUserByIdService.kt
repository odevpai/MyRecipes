package br.com.domain.services.user

import br.com.domain.entity.User
import br.com.infra.repository.user.UserReadOnlyRepository

class GetUserByIdService(
    private val userReadOnlyRepository: UserReadOnlyRepository
) {
    suspend fun getUserById(id: String): User? {
        return userReadOnlyRepository.findById(id)
    }
}