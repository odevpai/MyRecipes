package br.com.domain.validations

import br.com.application.payloads.request.AddUserRequest
import br.com.application.payloads.response.SimpleResponse
import br.com.utils.ErrorCodes
import br.com.utils.SuccessCodes

fun interface AddValidationUserRequest {
    suspend fun validator(request: AddUserRequest): SimpleResponse
}

class AddValidationUserRequestImpl: AddValidationUserRequest {

    private val emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$".toRegex()
    private val phoneRegex = "[0-9]{2} [1-9]{1} [0-9]{4}-[0-9]{4}".toRegex()

    override suspend fun validator(request: AddUserRequest): SimpleResponse {
        return when {
            request.name.isEmpty() -> SimpleResponse(
                successful = false,
                message = ErrorCodes.NAME_REQUIRED.message
            )

            request.email.isEmpty() -> SimpleResponse(
                successful = false,
                message = ErrorCodes.EMAIL_REQUIRED.message
            )

            !emailRegex.matches(request.email) -> SimpleResponse(
                successful = false,
                message = ErrorCodes.INVALID_EMAIL.message
            )

            request.password.isEmpty() -> SimpleResponse(
                successful = false,
                message = ErrorCodes.PASSWORD_REQUIRED.message
            )

            request.password.length < 6 -> SimpleResponse(
                successful = false,
                message = ErrorCodes.PASSWORD_TOO_SHORT.message
            )

            request.phone.isEmpty() -> SimpleResponse(
                successful = false,
                message = ErrorCodes.PHONE_REQUIRED.message
            )

            !phoneRegex.matches(request.phone) -> SimpleResponse(
                successful = false,
                message = ErrorCodes.INVALID_PHONE_FORMAT.message
            )

            else -> SimpleResponse(
                successful = true,
                message = SuccessCodes.VALID_REGISTRATION.message
            )
        }
    }
}