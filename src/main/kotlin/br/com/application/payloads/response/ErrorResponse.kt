package br.com.application.payloads.response

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val message: String,
    val httpStatusCode: Int
)
