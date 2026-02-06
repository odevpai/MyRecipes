package br.com.application.payloads.response

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val id: String,
    val name: String,
    val email: String,
    val phone: String,
    val createdAt: String
)
