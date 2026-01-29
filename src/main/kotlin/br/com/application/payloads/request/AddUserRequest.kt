package br.com.application.payloads.request

data class AddUserRequest(
    val name: String,
    val email: String,
    val password: String,
    val phone: String
)
