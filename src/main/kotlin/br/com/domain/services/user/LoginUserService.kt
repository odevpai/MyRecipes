package br.com.domain.services.user

import br.com.application.payloads.request.AuthUserRequest
import br.com.application.payloads.response.TokenResponse
import br.com.domain.services.password.BCryptPasswordService
import br.com.domain.services.token.TokenService
import br.com.domain.validations.AuthUserRequestValidation
import br.com.infra.repository.user.UserReadOnlyRepository
import br.com.utils.ErrorCodes
import br.com.utils.SuccessCodes

class LoginUserService(
    private val tokenService: TokenService,
    private val bCryptPasswordService: BCryptPasswordService,
    private val authUserRequestValidation: AuthUserRequestValidation,
    private val userReadOnlyRepository: UserReadOnlyRepository
) {
    suspend fun login(request: AuthUserRequest): TokenResponse {

        val tokenResponse = authUserRequestValidation.validator(request)
        if (!tokenResponse.successful) return tokenResponse

        val user = userReadOnlyRepository.checkIfExistsReturn(request.email) ?:
        return TokenResponse(
            false,
            message = ErrorCodes.USER_EMAIL_NOT_FOUND.message
        )

        val hashPassword = user.password
        val verifyPassword = bCryptPasswordService.verify(request.password.toCharArray(), hashPassword)
        if (verifyPassword) {
            val token = tokenService.generateToken(user.id)
            return TokenResponse(
                true,
                message = SuccessCodes.LOGIN_SUCCESS.message,
                token = token
            )
        }
        return TokenResponse(false, message = ErrorCodes.INCORRECT_PASSWORD.message)
    }
}