package com.sostisoft

import com.sostisoft.domain.Permission
import com.sostisoft.domain.User
import com.sostisoft.ports.security.TokenManager
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.stereotype.Component
import java.util.Date

@Component
class JwtTokenManager(
    @Value("\${jwt.secret}") private val secret: String,
    @Value("\${jwt.expiration}") private val expiration: Long,
) : TokenManager {

    override fun validateToken(token: String): Permission {
        try {
            val claims = parseToken(token)
            return Permission(!claims.expiration.before(Date()), claims["isAdmin"] as Boolean)
        } catch (e: Exception) {
            return Permission(false, false)
        }
    }

    private fun parseToken(token: String): Claims {
        return Jwts.parser()
            .verifyWith(Keys.hmacShaKeyFor(secret.toByteArray()))
            .build()
            .parseSignedClaims(token)
            .payload
    }

    override fun generateToken(user: User): String {
        return Jwts.builder()
            .subject(user.userName)
            .claim("isAdmin", user.isAdmin)
            .issuedAt(Date())
            .expiration(Date(System.currentTimeMillis() + expiration))
            .signWith(Keys.hmacShaKeyFor(secret.toByteArray(Charsets.UTF_8)))
            .compact()
    }
}