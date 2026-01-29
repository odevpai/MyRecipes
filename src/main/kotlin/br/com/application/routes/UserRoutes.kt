package br.com.application.routes

import br.com.application.payloads.request.AddUserRequest
import br.com.domain.services.AddUserService
import io.ktor.client.plugins.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRoutes(
    addUserService: AddUserService
) {
    route("/user") {
        createUser(addUserService)
    }
}

fun Route.createUser(addUserService: AddUserService){
    post("/register") {
        try {
            val request = call.receiveNullable<AddUserRequest>()
            if (request != null) {
                val simpleResponse = addUserService.addUser(request)
                if (simpleResponse.succesful) {
                    call.respond(HttpStatusCode.Created, simpleResponse)
                } else {
                    call.respond(HttpStatusCode.BadRequest, simpleResponse)
                }
            } else {
                call.respond(HttpStatusCode.BadRequest, HttpStatusCode.InternalServerError)
            }
        } catch (e: ServerResponseException) {
            application.log.error("Error creating user: ${e.message}")
            call.respond(HttpStatusCode.BadRequest)
        }
    }
}