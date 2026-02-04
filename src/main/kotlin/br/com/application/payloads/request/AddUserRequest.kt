package br.com.application.payloads.request

import kotlinx.serialization.Serializable

@Serializable
data class AddUserRequest(
    val name: String,
    val email: String,
    val password: String,
    val phone: String
)
