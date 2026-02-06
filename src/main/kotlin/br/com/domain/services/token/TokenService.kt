package br.com.domain.services.token

import br.com.utils.Constants
import br.com.utils.ErrorCodes
import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException

class TokenService {
    private val issuer = "MyRecipesServer"
    private val realm = "project.recipes"
    private val audience = "MyRecipesApp"
    private val jwtSecret = System.getenv(Constants.SECRET)
    private val algorithm = Algorithm.HMAC256(jwtSecret)
    private val verifier = JWT.require(algorithm).withIssuer(issuer).build()

    fun realm() = realm
    fun audience() = audience
    fun verifier(): JWTVerifier = verifier

    fun generateToken(userId: String): String {
        return try {
            println("DEBUG - Gerando token para userId: $userId")
            println("DEBUG - Issuer: $issuer")
            println("DEBUG - Audience: $audience")

            val token = JWT.create()
                .withSubject(userId)
                .withIssuer(issuer)
                .withAudience(audience)
                .sign(algorithm)

            println("DEBUG - Token gerado com sucesso")
            token

        } catch (e: JWTVerificationException) {
            println("DEBUG - Erro ao gerar token: ${e.message}")
            throw IllegalArgumentException(ErrorCodes.TOKEN_GENERATION_ERROR.message)
        }
    }

}