package br.com.application.payloads.response

import kotlinx.serialization.Serializable

@Serializable
data class TokenResponse(
    val successful: Boolean,
    val message: String? = null,
    val token: String? = null
)
