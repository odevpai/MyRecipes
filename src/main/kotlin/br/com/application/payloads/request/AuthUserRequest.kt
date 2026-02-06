package br.com.application.payloads.request

import kotlinx.serialization.Serializable

@Serializable
data class AuthUserRequest(
    val email: String,
    val password: String

)
