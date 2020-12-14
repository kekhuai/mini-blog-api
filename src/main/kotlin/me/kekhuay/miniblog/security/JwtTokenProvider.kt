package me.kekhuay.miniblog.security

import io.jsonwebtoken.*
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.lang.IllegalArgumentException
import java.util.*

@Component
class JwtTokenProvider(
    @Value(value = "\${app.jwt.secret}")
    private val jwtSecret: String,
    @Value(value = "\${app.jwt.expiration-in-ms}")
    private val jwtExpirationInMs: Int
) {
    companion object {
        private val LOGGER = LoggerFactory.getLogger(this::class.java)
    }

    fun generateToken(authentication: Authentication): String {
        val userPrincipal: UserPrincipal = authentication.principal as UserPrincipal
        val now = Date()
        val expiryDate = Date(now.time + jwtExpirationInMs)
        return Jwts.builder()
            .setSubject(userPrincipal.getId().toString())
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact()
    }

    fun getUserIdFromJWT(token: String): Long {
        val claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).body
        return claims.subject.toLong()
    }

    fun validateToken(authToken: String): Boolean {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken)
            return true
        } catch (ex: SignatureException) {
            LOGGER.error("Invalid JWT signature")
        } catch (ex: MalformedJwtException) {
            LOGGER.error("Invalid JWT token")
        } catch (ex: ExpiredJwtException) {
            LOGGER.error("Expired JWT token")
        } catch (ex: UnsupportedJwtException) {
            LOGGER.error("Unsupported JWT token")
        } catch (ex: IllegalArgumentException) {
            LOGGER.error("JWT claims string is empty.")
        }
        return false
    }
}
