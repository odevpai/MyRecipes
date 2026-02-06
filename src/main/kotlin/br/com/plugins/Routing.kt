package br.com.plugins

import br.com.domain.services.user.AddUserService
import io.ktor.server.application.*
import org.koin.ktor.ext.inject
import io.ktor.server.routing.routing
import br.com.application.routes.userRoutes
import br.com.domain.services.user.GetUserProfileService
import br.com.domain.services.user.LoginUserService

fun Application.configureRouting() {

    val addUserService by inject<AddUserService>()
    val loginUserService by inject<LoginUserService>()
    val getUserProfileService by inject<GetUserProfileService>()

    routing {
        userRoutes(
            addUserService,
            loginUserService,
            getUserProfileService
        )

    }
}
