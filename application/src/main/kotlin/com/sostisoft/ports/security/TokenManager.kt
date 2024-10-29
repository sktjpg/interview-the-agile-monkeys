package com.sostisoft.ports.security

import com.sostisoft.domain.User

interface TokenManager {

    fun canAccessResource(token: String): Boolean

    fun generateToken(user: User): String

}