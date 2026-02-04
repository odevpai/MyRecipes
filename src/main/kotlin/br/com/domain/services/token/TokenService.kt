package br.com.domain.services.token

import br.com.utils.ErrorCodes
import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException

class TokenService {
    private val issuer = "MyRecipesServer"
    private val realm = "project.recipes"
    private val audience = "MyRecipesApp"
//    TODO: configurar variaveis de ambiente
    private val jwtSecret = System.getenv("Constants.SECRET")
    private val algorithm = Algorithm.HMAC256(jwtSecret)
    private val verifier = JWT.require(algorithm).withIssuer(audience).build()

    fun realm() = realm
    fun audience() = audience
    fun verifier(): JWTVerifier = verifier

    fun generatedToken(userId: String): String {
        return try {

            JWT.create()
                .withSubject(userId)
                .withIssuer(issuer)
                .withAudience(audience)
                .sign(algorithm)

        } catch (_: JWTVerificationException) {
            throw IllegalArgumentException(ErrorCodes.TOKEN_GENERATION_ERROR.message)
        }
    }

    fun retrieveIdFromToken(token: String): String {
        return try {

            val decodedJWT = JWT.require(algorithm)
                .withAudience(audience)
                .withIssuer(issuer)
                .build()
                .verify(token)

            decodedJWT.subject
        } catch (_: JWTVerificationException) {
            throw IllegalArgumentException(ErrorCodes.INVALID_TOKEN.message)
        }
    }

}