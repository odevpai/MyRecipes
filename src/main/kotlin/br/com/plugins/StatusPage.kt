package br.com.plugins

import br.com.application.payloads.response.ErrorResponse
import br.com.domain.exceptions.UserAuthNotFoundException
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun Application.configureStatusPage() {
    install(StatusPages) {

        status(HttpStatusCode.NotFound) { call, cause ->
            val errorResponse = ErrorResponse(
                httpStatusCode = HttpStatusCode.NotFound.value,
                message = cause.description
            )
            call.respond(
                message = errorResponse,
                status = HttpStatusCode.NotFound
            )
        }

        status(HttpStatusCode.Unauthorized) { call, cause ->
            val errorResponse = ErrorResponse(
                httpStatusCode = HttpStatusCode.Unauthorized.value,
                message = cause.description
            )
            call.respond(
                message = errorResponse,
                status = HttpStatusCode.Unauthorized
            )
        }

        status(HttpStatusCode.Conflict) { call, cause ->
            val errorResponse = ErrorResponse(
                httpStatusCode = HttpStatusCode.Conflict.value,
                message = cause.description
            )
            call.respond(
                message = errorResponse,
                status = HttpStatusCode.Conflict
            )
        }

        exception<IllegalArgumentException> { call, cause ->
            val errorResponse = ErrorResponse(
                httpStatusCode = HttpStatusCode.BadRequest.value,
                message = cause.message.orEmpty()
            )
            call.respond(
                message = errorResponse,
                status = HttpStatusCode.BadRequest
            )
        }

        exception<UserAuthNotFoundException> { call, cause ->
            val errorResponse = ErrorResponse(
                httpStatusCode = HttpStatusCode.NotFound.value,
                message = cause.message.orEmpty()
            )
            call.respond(
                message = errorResponse,
                status = HttpStatusCode.NotFound
            )
        }
    }
}