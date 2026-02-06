package br.com.application.payloads.response

class TokenResponseFactory {
    fun create(successful: Boolean, message: String, token: String): TokenResponse {
        return TokenResponse(successful, message, token)
    }
}