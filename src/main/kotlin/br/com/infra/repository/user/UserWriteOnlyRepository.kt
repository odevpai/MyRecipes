package br.com.infra.repository.user

import br.com.domain.entity.User

interface UserWriteOnlyRepository {
    suspend fun save(user: User): Boolean
}