package br.com.domain.services.user

import br.com.application.payloads.request.AddUserRequest
import br.com.application.payloads.response.SimpleResponse
import br.com.domain.entity.User
import br.com.domain.services.password.BCryptPasswordService
import br.com.domain.validations.AddUserRequestValidation
import br.com.infra.repository.user.UserReadOnlyRepository
import br.com.infra.repository.user.UserWriteOnlyRepository
import br.com.utils.ErrorCodes
import br.com.utils.SuccessCodes

class AddUserService(
    private val addUserRequestValidation: AddUserRequestValidation,
    private val userWriteOnlyRepository: UserWriteOnlyRepository,
    private val userReadOnlyRepository: UserReadOnlyRepository,
    private val bCryptPasswordService: BCryptPasswordService
) {

    suspend fun addUser(addUserRequest: AddUserRequest): SimpleResponse {

        val simpleResponse = addUserRequestValidation.validator(addUserRequest)
        if (!simpleResponse.successful)
            return simpleResponse

        if (userReadOnlyRepository.checkIfExists(addUserRequest.email))
            return SimpleResponse(false, message = ErrorCodes.EMAIL_ALREADY_USED.message)

        val hashedPassword = bCryptPasswordService.hash(addUserRequest.password.toCharArray())

        val user = User(
            name = addUserRequest.name,
            email = addUserRequest.email,
            password = hashedPassword,
            phone = addUserRequest.phone,
        )

        val result = userWriteOnlyRepository.save(user)

        return if(result) SimpleResponse(true, message = SuccessCodes.REGISTRATION_COMPLETED.message) else SimpleResponse(false)

    }

}