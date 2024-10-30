package com.sostisoft.ports.security

import com.sostisoft.domain.Permission
import com.sostisoft.domain.User

interface TokenManager {

    fun validateToken(token: String): Permission?

    fun generateToken(user: User): String

}