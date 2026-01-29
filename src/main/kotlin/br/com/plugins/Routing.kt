package br.com.plugins

import br.com.domain.services.AddUserService
import io.ktor.server.application.*
import org.koin.ktor.ext.inject
import io.ktor.server.routing.routing
import br.com.application.routes.userRoutes

fun Application.configureRouting() {

    val addUserService by inject<AddUserService>()

    routing {
        userRoutes(addUserService)
    }
}
