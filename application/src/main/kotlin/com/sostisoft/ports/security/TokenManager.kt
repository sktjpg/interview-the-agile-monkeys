package com.sostisoft.ports.security

import com.sostisoft.domain.User

interface TokenManager {

    fun canAccessResource(token: String, isAdmin: Boolean): Boolean

    fun generateToken(user: User): String

}