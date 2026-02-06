package br.com.application.routes

import br.com.application.payloads.request.AddUserRequest
import br.com.application.payloads.request.AuthUserRequest
import br.com.domain.extensions.getUserAuthentication
import br.com.domain.services.user.AddUserService
import br.com.domain.services.user.GetUserProfileService
import br.com.domain.services.user.LoginUserService
import br.com.utils.Constants
import io.ktor.client.plugins.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRoutes(
    addUserService: AddUserService,
    loginUserService: LoginUserService,
    getProfilesUserService: GetUserProfileService
) {
    route(Constants.USER_ROUTE) {
        authenticate {
            getUserProfile(getProfilesUserService)
        }
        createUser(addUserService)
        loginUser(loginUserService)
    }
}

fun Route.getUserProfile(getUserProfileService: GetUserProfileService) {
    get("/profile") {
        try {
            val userId = call.getUserAuthentication()
            val userResponse = getUserProfileService.getProfileUserById(userId)
            call.respond(HttpStatusCode.OK, userResponse)
        } catch (e: ServerResponseException) {
            application.log.error("Error creating user: ${e.message}")
            call.respond(HttpStatusCode.Unauthorized)
        }
    }
}

fun Route.loginUser(loginUserService: LoginUserService) {
    post("/login") {
        try {
            val request = call.receiveNullable<AuthUserRequest>()
            if (request != null) {
                val simpleResponse = loginUserService.login(request)
                if (simpleResponse.successful) {
                    call.respond(HttpStatusCode.OK, simpleResponse)
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

fun Route.createUser(addUserService: AddUserService) {
    post("/register") {
        try {
            val request = call.receiveNullable<AddUserRequest>()
            if (request != null) {
                val simpleResponse = addUserService.addUser(request)
                if (simpleResponse.successful) {
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