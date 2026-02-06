package br.com.application.payloads.request

class AuthUserRequestFactory {
    fun create(email: String, password: String): AuthUserRequest {
        return AuthUserRequest(
            email = email,
            password = password
        )
    }
}