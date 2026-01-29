package br.com.application.payloads.response

data class SimpleResponse(
    val succesful: Boolean,
    val message: String? = null
)
