package br.com.domain.services

import br.com.application.payloads.request.AddUserRequest
import br.com.application.payloads.response.SimpleResponse
import br.com.domain.entity.User
import br.com.infra.repository.user.UserReadOnlyRepository
import br.com.infra.repository.user.UserWriteOnlyRepository
import kotlin.time.ExperimentalTime

class AddUserService(
    private val userWriteOnlyRepository: UserWriteOnlyRepository,
    private val userReadOnlyRepository: UserReadOnlyRepository
) {

    @OptIn(ExperimentalTime::class)
    suspend fun addUser(addUserRequest: AddUserRequest): SimpleResponse {
        val user = User(
            name = addUserRequest.name,
            email = addUserRequest.email,
            password = addUserRequest.password,
            phone = addUserRequest.phone
        )

        val result = userWriteOnlyRepository.save(user)

        return if(result) SimpleResponse(true, message = "User created successfully!") else SimpleResponse(false)

    }

}