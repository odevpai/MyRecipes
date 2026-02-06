package br.com.domain.exceptions

class UserAuthNotFoundException(
    override val message: String?
): RuntimeException()