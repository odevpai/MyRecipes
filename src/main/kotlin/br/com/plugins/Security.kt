package br.com.plugins

import br.com.domain.entity.User
import br.com.domain.services.token.TokenService
import br.com.domain.services.user.GetUserByIdService
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import org.koin.ktor.ext.inject

fun Application.configureSecurity() {
    val getUserByIdService by inject<GetUserByIdService>()
    val tokenService by inject<TokenService>()

    authentication {
        jwt {
            realm = tokenService.realm()
            verifier(tokenService.verifier())
            validate {
                jwtCredential ->
                validateCredential(jwtCredential, tokenService, getUserByIdService)
            }
        }
    }
}

suspend fun validateCredential(
    jwtCredential: JWTCredential,
    tokenService: TokenService,
    getUserByIdService: GetUserByIdService
): User {
    if (jwtCredential.audience.contains(tokenService.audience())) {
        val subject = jwtCredential.subject
        if (!subject.isNullOrEmpty()) {
            val user = getUserByIdService.getUserById(subject)
            if (user != null) {
                return user
            } else {
                throw IllegalArgumentException("Usuario nao encontrado para o ID: $subject")
            }
        } else {
            throw IllegalArgumentException("Subject nulo ou vazio")
        }
    } else {
        throw IllegalArgumentException("Audiencia invalida")
    }
}
