package br.com.infra.repository.user

import br.com.domain.entity.User

interface UserReadOnlyRepository {
    suspend fun findById(id: String): User?
    suspend fun findByIds(ids: List<String>): List<User>?
    suspend fun checkIfExists(email: String): Boolean
    suspend fun checkIfExistsReturn(email: String): User?
}