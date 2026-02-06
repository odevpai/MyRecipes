package br.com.domain.extensions

import br.com.domain.entity.User
import br.com.domain.exceptions.UserAuthNotFoundException
import br.com.utils.ErrorCodes
import io.ktor.server.application.*
import io.ktor.server.auth.*

fun ApplicationCall.getUserAuthentication(): String {
    val user = authentication.principal<User>()
    if (user != null) {
        return user.id
    } else {
        throw UserAuthNotFoundException(ErrorCodes.USER_NOT_LOGGED_IN.message)
    }
}